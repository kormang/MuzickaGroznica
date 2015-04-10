package net.etfbl.muzickagroznica.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import net.etfbl.muzickagroznica.controller.utils.entities.MusicContentExtraCount;
import net.etfbl.muzickagroznica.model.entities.MusicContent;
import net.etfbl.muzickagroznica.service.ContentService;
import net.etfbl.muzickagroznica.service.UserService;
import net.etfbl.muzickagroznica.util.StandardUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReportsController extends MuzickaGroznicaController {

	@Autowired
	ContentService contentService;
	
	@Autowired
	UserService userService;
	
	public ReportsController() {
		// TODO Auto-generated constructor stub
	}

	
	@RequestMapping(value="/admin/reports")
	public String showReports(){
		
		return "admin/reports";
	}
	
	@RequestMapping(value="/admin/reports/get_top_rated", produces="application/json; charset=UTF-8")
	public @ResponseBody String getTopRated(){
		List<MusicContentExtraCount> mcrs = loadTopRated(30);
		return musicContentExtraCountListToJsonArray(mcrs);
	}
	
	@RequestMapping(value="/admin/reports/get_most_favored", produces="application/json; charset=UTF-8")
	public @ResponseBody String getMostFavored(){
		List<MusicContentExtraCount> mcrs = loadMostFavored(30);
		return musicContentExtraCountListToJsonArray(mcrs);
	}
	
	@RequestMapping(value="/admin/reports/get_monthly_report_data", produces="application/json; charset=UTF-8")
	public @ResponseBody String getMonthlyReportData(){
		LocalDate today = LocalDate.now();
		LocalDate monthBefore = today.minusMonths(1);
		java.util.Date targetDate = java.util.Date.from(monthBefore.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		
		int numUsers = userService.findRegisteredAfter(targetDate).size();
		int numContent = contentService.findMusicContentAddedAfter(targetDate).size();
		
		return "{ \"u\": "+numUsers+", \"c\": "+numContent+" }";
	}
	
	private String musicContentExtraCountListToJsonArray(List<MusicContentExtraCount> mcrs){
		StringBuilder sb = new StringBuilder("[");
		boolean f = false;
		
		for(MusicContentExtraCount mcr : mcrs){
			if(f){
				sb.append(", ");
			}else{
				f = true;
			}
			sb.append("{ \"name\": \"");
			sb.append(mcr.getName());
			sb.append("\", \"artist\": \"");
			sb.append(mcr.getArtistName());
			sb.append("\", \"genre\": \"");
			sb.append(mcr.getGenreName());
			sb.append("\", \"contentType\": \"");
			sb.append(mcr.getContentType());
			sb.append("\", \"publishDate\": \"");
			sb.append(mcr.getPublishTime());
			sb.append("\", \"extraCount\": \"");
			sb.append(mcr.getExtraCount());
			sb.append("\"}");
		}
		
		sb.append("]");
		
		return sb.toString();
	}
	
	private void transferBasicProperties(MusicContentExtraCount mcr, MusicContent mc){
		mcr.setArtistName(mc.getArtistName());
		mcr.setGenreName(mc.getGenreName());
		String contentType;
		switch(mc.getContentType()){
		
			case 0:
				contentType = "MP3";
				break;
			case 1:
				contentType = "YouTube";
				break;
			case 2:
				contentType = "Soundcloud";
				break;
			default:
				throw new RuntimeException("Unknown content type.");
		}
		
		mcr.setContentType(contentType);
		
		mcr.setId(mc.getId());
		mcr.setName(mc.getName());
		mcr.setPublishTime(mc.getPublishTime());
	}
	
	private List<MusicContentExtraCount> loadTopRated(int n){
		List<MusicContent> toprated = contentService.findTopNMusicContent(n);
		List<MusicContentExtraCount> merged = new ArrayList<MusicContentExtraCount>(n);
		
		for(MusicContent mc : toprated){
			MusicContentExtraCount mcr = new MusicContentExtraCount();
			transferBasicProperties(mcr, mc);
			mcr.setExtraCount(contentService.calculateRatingForMusicContent(mc.getId()));
			
			merged.add(mcr);
		}
		
		return merged;
	}
	
	private List<MusicContentExtraCount> loadMostFavored(int n){
		List<MusicContent> mostfavored = contentService.findNMostFavoredMusicContent(n);
		List<MusicContentExtraCount> merged = new ArrayList<MusicContentExtraCount>(n);
		
		for(MusicContent mc : mostfavored){
			MusicContentExtraCount mcr = new MusicContentExtraCount();
			transferBasicProperties(mcr, mc);
			mcr.setExtraCount(contentService.findNumberOfFavoritesForMusicContent(mc.getId()));
			
			merged.add(mcr);
		}
		
		return merged;
	}
}
