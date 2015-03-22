package net.etfbl.muzickagroznica.service;

import net.etfbl.muzickagroznica.model.dao.ArtistDao;
import net.etfbl.muzickagroznica.model.dao.GenreDao;
import net.etfbl.muzickagroznica.model.dao.MusicContentDao;
import net.etfbl.muzickagroznica.model.dao.UserDao;
import net.etfbl.muzickagroznica.model.entities.Artist;
import net.etfbl.muzickagroznica.model.entities.Genre;
import net.etfbl.muzickagroznica.model.entities.MusicContent;
import net.etfbl.muzickagroznica.model.entities.User;
import net.etfbl.muzickagroznica.util.StandardUtil;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Service
public class ContentService {

	@Autowired
	GenreDao genreDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	MusicContentDao musicContentDao;
	
	@Autowired
	ArtistDao artistDao;
	
	public ContentService() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	public Genre addGenre(String name){
		Genre genre = new Genre(name);
		genreDao.persist(genre);
		return genre;
		
	}
	
	@Transactional
	public List<Genre> findAllGenres(){
		return genreDao.findAll();
	}
	
	
	@Transactional
	public MusicContent addNewContent(
			String name,
			String artist,
			String genre,
			String lyrics,
			String contentPath,
			int publisherId
	){
	
		MusicContent newContent = new MusicContent();
		User user = userDao.findById(publisherId);
		
		java.util.Date length = null;
		
		int contentType = findContentType(contentPath);
		
		if(contentType == -1){
			throw new RuntimeException("Unknown content type.");
		}
		
		try{
			if(contentType == 1){
				length = findYoutubeVideoDuration(contentPath);
			}else{
				length = findSoundcloudTrackDuration(contentPath);
			}
			
		}catch(Exception ex){
			throw new RuntimeException("Could not find content duration.", ex);
		}
		
				
		Artist artistEntity = artistDao.findById(artist);
		
		if(artistEntity == null){
			artistEntity = new Artist(artist);
			artistDao.persist(artistEntity);
		}
		
		Genre genreEntity = genreDao.findById(genre);
		if(genreEntity == null){
			throw new RuntimeException("No such genre");
		}
		
		newContent.setActive(true);
		newContent.setArtist(artistEntity);
		newContent.setUser(user);
		newContent.setGenre(genreEntity);
		newContent.setContentPath(contentPath);
		newContent.setContentType(contentType);
		newContent.setName(name);
		newContent.setPublishTime(StandardUtil.now());
		newContent.setLength(length);
		newContent.setLyrics(lyrics);
		
		musicContentDao.persist(newContent);
			
		return newContent;
	}
	
	protected static String youtubeApiv3RequestTemplate = "https://www.googleapis.com/youtube/v3/videos?part=contentDetails&id=<<VIDEO_ID>>&key=AIzaSyBiXLRaheZSITODhyBeCAuzSrEtkyJ19zU";
	
	/**
	 * 
	 * @param contentPath url link to content
	 * @return 1 if it's youtube, 2 if it's soundcloud, -1 otherwise
	 */
	protected int findContentType(String contentPath){
		if(contentPath.matches("http(s)?://www\\.youtube\\.com/.+")){
			return 1;
		}else if(contentPath.matches("http(s)?://www\\.soundcloud\\.com/.+")){
			return 2;
		}else{
			return -1;
		}
	}
	
	protected java.util.Date findSoundcloudTrackDuration(String trackUrl){
		throw new UnsupportedOperationException("Soundcloud track duration is not implemented.");
	}
	
	protected java.util.Date findYoutubeVideoDuration(String videoUrl) throws Exception{
		
		String videoId = extractYoutubeVideoId(videoUrl);
		
		String requrlstr = youtubeApiv3RequestTemplate.replace("<<VIDEO_ID>>", videoId);
		
		URL reqUrl = new URL(requrlstr);
		
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(reqUrl.openStream()));
		
		StringBuilder sb = new StringBuilder();
		String line = null;
		
		while((line = reader.readLine())!=null){
			sb.append(line);
		}
		
		JSONObject jsonObj = new JSONObject(sb.toString());
		String durationString =	jsonObj.getJSONArray("items")
				.getJSONObject(0)
				.getJSONObject("contentDetails")
				.getString("duration");
		
		java.util.Date duration = parseYoutubeDurationString(durationString);
		
		return duration;
	}
		
	protected java.util.Date parseYoutubeDurationString(String durationString){
		// The beauty of java's date and time APIs !
		// Well, at least new java.time package makes this "possible":
		Duration duration = Duration.parse(durationString);
		LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(duration.toMillis()), ZoneId.ofOffset("UTC", ZoneOffset.ofHours(0)));
		return StandardUtil.fromLocalDateTime(ldt);
	}
	
	protected String extractYoutubeVideoId(String url){
		URL urlobj = null;
		try {
			urlobj = new URL(url);
		} catch (MalformedURLException e) {
			throw new RuntimeException("Invalid URL", e);
		}
		
		String query = urlobj.getQuery();
		String[] queries = query.split("&");
		
		String idquery = null;
		
		for(String q : queries){
			if(q.startsWith("v=")){
				idquery = q;
				break;
			}
		}
		
		String idvalue = idquery.split("=")[1];
		
		return idvalue;

	}
}
