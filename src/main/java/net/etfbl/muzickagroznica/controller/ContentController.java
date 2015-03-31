package net.etfbl.muzickagroznica.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.etfbl.muzickagroznica.form.bean.ContentNewForm;
import net.etfbl.muzickagroznica.form.bean.SearchForm;
import net.etfbl.muzickagroznica.model.dao.RateDao;
import net.etfbl.muzickagroznica.model.entities.Comment;
import net.etfbl.muzickagroznica.model.entities.Favorite;
import net.etfbl.muzickagroznica.model.entities.Genre;
import net.etfbl.muzickagroznica.model.entities.MusicContent;
import net.etfbl.muzickagroznica.model.entities.Rate;
import net.etfbl.muzickagroznica.model.entities.User;
import net.etfbl.muzickagroznica.security.AuthUser;
import net.etfbl.muzickagroznica.service.ContentService;
import net.etfbl.muzickagroznica.util.StandardUtilsBean;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController extends MuzickaGroznicaController {
	
	@Autowired
	ContentService contentService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	StandardUtilsBean standardUtilsBean;

	public ContentController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value="/admin/genres")
	public String viewGenres(Map<String, Object> model){
		List<Genre> genres = contentService.findAllGenres();
		model.put("genres", genres);
		return "admin/genres";
	}
	
	@RequestMapping(value="/admin/add_genre", produces="application/json; charset=UTF-8")
	public @ResponseBody String addGenre(
			Locale local,
			@RequestParam("name") String name
			){
		
		String msg = messageSource.getMessage("admin.add_genre.success", null, local);
		boolean result = true;
		
		try{
			contentService.addGenre(name);
		}catch(RuntimeException ex){
			msg = messageSource.getMessage("admin.add_genre.error", null, local);
			result = false;
		}
		
		
		return "{ \"result\" : "+result+", \"message\" : \""+msg+"\"}";
	}
	
	@RequestMapping(value="/content/new", method=RequestMethod.GET)
	public String viewNewContent(Map<String, Object> model){
		model.put("contentNewForm", new ContentNewForm());
		putGenresInModel(model);
		return "content/new";
	}
	
	@RequestMapping(value="/super/audio_file_upload", method=RequestMethod.GET)
	public String viewAudioFileUpload(Map<String, Object> model){
		putGenresInModel(model);
		return "super/audio_file_upload";
	}
	
	@RequestMapping(value="/super/audio_upload_error")
	public String viewAudioUploadError(){
		return "super/audio_upload_error";
	}
	
	private void putGenresInModel(Map<String, Object> model){
		List<Genre> genres = contentService.findAllGenres();
		model.put("genres", genres
								.stream()
								.map(new Function<Genre, String>() {

									@Override
									public String apply(Genre t) {
										return t.getName();
									}
									
								})
								.collect(Collectors.toList()));
	}

	@RequestMapping(value="/content/new", method=RequestMethod.POST)
	public String addNewContent(
			@Valid @ModelAttribute("contentNewForm") ContentNewForm contentNewForm,
			HttpSession session
			){
		
		User user = (User) session.getAttribute("user");
		
		MusicContent mc = contentService.addNewContent(
				contentNewForm.getName(),
				contentNewForm.getArtist(),
				contentNewForm.getGenre(),
				contentNewForm.getLyrics(),
				contentNewForm.getContentPath(),
				user.getId()
		);
		
		
		return "redirect:/content/listen/" + mc.getId();
	}
	
	@RequestMapping(value="/content/listen/{content_id}")
	public String listen(
			Map<String, Object> model,
			@PathVariable("content_id") int contentId,
			HttpServletRequest request,
			Locale local,
			HttpSession session
	){
		
		MusicContent musicContent = contentService.findMusicContentById(contentId);
		String embeddCode;
		
		switch (musicContent.getContentType()) {
		case 0:
			String url = request.getContextPath() + "/contents/" + musicContent.getContentPath();
			embeddCode = audioFileEmbeddTemplate.replace("<<FILE_PATH>>", url).replace("<<CONTEXT_PATH>>", request.getContextPath());
			break;
		case 1:
			embeddCode = youtubeEmbeddTemplate.replace("<<VIDEO_ID>>", musicContent.getExtraInfo());
			break;
		case 2:
			embeddCode = soundcloudEmbeddTemplate.replace("<<TRACK_ID>>", musicContent.getExtraInfo());
			break;

		default:
			throw new RuntimeException("This should not happen, contentType is not in [0, 2]");
		}
		
		model.put("embeddCode", embeddCode);
		model.put("musicContent", musicContent);
		
		DateFormat df = new SimpleDateFormat(messageSource.getMessage("muzickagroznica.dateFormat", null, local));
		
		model.put("publishDate", df.format(musicContent.getPublishTime()));
		model.put("duration", DurationFormatUtils.formatDurationHMS(musicContent.getDuration().getTime()));
		model.put("name", musicContent.getName());
		model.put("lyrics", musicContent.getLyrics());
		model.put("genreName", musicContent.getGenreName());
		model.put("artistName", musicContent.getArtistName());
		model.put("musicContentId", contentId);
		
		
		User user = (User) session.getAttribute("user");
		
		Favorite favorite = contentService.findFavorite(user.getId(), contentId);
		model.put("favorite", favorite != null);
		
		Rate rate = contentService.findRate(user.getId(), contentId);
		System.err.println("rate: " + rate);
		if(rate != null){
			model.put("rateValue", rate.getRate());
		}

		return "content/listen";
	}
	
	
	@RequestMapping(value="/content/search")
	public String searchContent(
			@ModelAttribute("searchForm") SearchForm searchForm,
			Map<String, Object> model,
			Locale local
	){
		if(searchForm == null){
			searchForm = new SearchForm();
			model.put("searchForm", searchForm);
			return "content/search_results";
		}
		
		List<MusicContent> searchResults = contentService.searchForMusicContent(
					searchForm.getName(),
					searchForm.getArtist(),
					searchForm.getGenre()
		);
		
		model.put("searchResults", searchResults);
		ArrayList<String> formattedDates = new ArrayList<String>();
		
		DateFormat df = new SimpleDateFormat(messageSource.getMessage("muzickagroznica.dateFormat", null, local));

		for(MusicContent mc : searchResults){
			formattedDates.add(df.format(mc.getPublishTime()));
		}
		
		model.put("formattedDates", formattedDates);
		
		
		return "content/search_results";
	}
	
	@RequestMapping(value="/content/favorite", produces="application/json; charset=UTF-8")
	public @ResponseBody String markFavorite(
			@RequestParam("onoff") boolean onoff,
			@RequestParam("mcid") int musicContentId,
			HttpSession session
	){
		System.err.println(onoff);
		System.err.println(musicContentId);
		int userId = ((User)session.getAttribute("user")).getId();
		
		Favorite favorite;
		boolean result;
		
		if(onoff){
			favorite = contentService.addToFavorites(userId, musicContentId);
		}else{
			favorite = contentService.removeFromFavorites(userId, musicContentId);
		}
		
		result = favorite != null;
		boolean state = (onoff == result); 
		
		return "{ \"result\" : "+result+", \"state\" : "+state+"}";
	}
	
	@RequestMapping(value="/content/rate", produces="application/json; charset=UTF-8")
	public @ResponseBody String rate(
			@RequestParam("rateval") int rateval,
			@RequestParam("mcid") int musicContentId,
			HttpSession session
	){
		User user = (User) session.getAttribute("user");
		 
		Rate rate = contentService.rate(user.getId(), musicContentId, rateval);
		boolean result = rate != null;
		int state = rateval;
		
		return "{ \"result\" : "+result+", \"state\" : "+state+"}";
	}
	
	@RequestMapping(value="/content/add_comment", produces="application/json; charset=UTF-8")
	public @ResponseBody String addComment(
			@RequestParam("mcid") int musicContentId,
			@RequestParam("commtext") String commentText,
			HttpSession session
	){
		boolean result = true;
		User user = (User)session.getAttribute("user");
		
		commentText = StringEscapeUtils.escapeHtml4(commentText);
		
		contentService.addComment(user.getId(), musicContentId, commentText);
		
		return "{ \"result\" : "+result+"}";
	}
	
	@RequestMapping(value="/content/comments")
	public String loadComments(
			@RequestParam("mcid") int musicContentId,
			Map<String, Object> model
	){
	
		List<Comment> comments = contentService.listComments(musicContentId);
		model.put("comments", comments);
		
		return "content/comments";
	}
	
	
	@RequestMapping(value="/content/delete_comment", produces="application/json; charset: UTF-8")
	public @ResponseBody String deleteComment(
		@RequestParam("commid") int commentId,
		HttpSession session
	){
		boolean result = false;
		
		if(session.getAttribute("ROLE_ADMIN") != null){
			result = contentService.deleteComment(commentId);
		}
		return "{ \"result\" : "+result+"}";
	}
	
	private static String audioFileEmbeddTemplate = "<div id=\"player_holder\" style=\"display: inline-block; width: 658px;\"><img id=\"eqvimg\" src=\"<<CONTEXT_PATH>>/images/audio-player-header.jpg\"></img><audio controls autoplay id=\"player\" style=\"display: block; margin-left: auto; margin-right: auto; margin: 0 auto; width: 100%\"><source src=\"<<FILE_PATH>>\">X</audio></div>";
 	private static String soundcloudEmbeddTemplate = "<iframe width=\"50%\" height=\"225\" scrolling=\"no\" frameborder=\"no\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/<<TRACK_ID>>&amp;auto_play=true&amp;hide_related=false&amp;show_comments=true&amp;show_user=true&amp;show_reposts=false&amp;visual=true\"></iframe>";
	private static String youtubeEmbeddTemplate = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/<<VIDEO_ID>>?autoplay=1\" frameborder=\"0\" allowfullscreen></iframe>";
}
