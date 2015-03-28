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
import net.etfbl.muzickagroznica.util.StandardUtilsBean;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;

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
	
	@Autowired
	StandardUtilsBean standardUtilsBean;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	
	protected static String youtubeApiv3RequestTemplate = "https://www.googleapis.com/youtube/v3/videos?part=contentDetails&id=<<VIDEO_ID>>&key=AIzaSyBiXLRaheZSITODhyBeCAuzSrEtkyJ19zU";
	protected static String soundcloudApiRequestTemplate = "https://api.soundcloud.com/resolve.json?url=<<TRACK_URL>>&client_id=95ca80e02ae6b877cf5f986b91b8abce";

	
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
			byte[] bytes,
			int publisherId
	){
		MusicContent newContent = new MusicContent();
		
		String uufilename = UUID.randomUUID().toString();
		
		File outputFile = new File(standardUtilsBean.getContentUploadDir(), uufilename);
		
		try {
			FileUtils.writeByteArrayToFile(outputFile, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		long millisLength;
		try {
			
			//Ad-hoc quick solution
			//first try with mp3spi's MpegAudioFileReader, and if it throws Unsupp...
			//then we try AudioSystem
			
			try {
				AudioFileFormat baseFileFormat = new MpegAudioFileReader().getAudioFileFormat(outputFile);
				Map<String, Object> properties = baseFileFormat.properties();
				millisLength = (Long) properties.get("duration");
			}catch(UnsupportedAudioFileException uafe){
				AudioFileFormat aff = AudioSystem.getAudioFileFormat(outputFile);
				Object o = aff.getProperty("duration");
				millisLength = 1000 * (long) o;
			}

			
		} catch (Exception ex){
			throw new RuntimeException("Could not get audio file duration.", ex);
		}
		
		java.util.Date length = java.util.Date.from(LocalDateTime
				.ofInstant(Instant.ofEpochMilli(millisLength), ZoneId.ofOffset("UTC", ZoneOffset.ofHours(0)))
				.atZone(ZoneId.systemDefault())
				.toInstant());
		
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus txstat = null;
		
		
		try {

			txstat = transactionManager.getTransaction(def);

			User user = userDao.findById(publisherId);
			Artist artistEntity = artistDao.findById(artist);
			
			if(artistEntity == null){
				artistEntity = new Artist(artist);
				artistDao.persist(artistEntity);
			}
			
			Genre genreEntity = genreDao.findById(genre);
			if(genreEntity == null){
				System.err.println("NO SUCH GENRE");
				throw new RuntimeException("No such genre");
			}

			newContent.setActive(true);
			newContent.setArtist(artistEntity);
			newContent.setUser(user);
			newContent.setGenre(genreEntity);
			newContent.setContentPath(uufilename);
			newContent.setContentType(0);
			newContent.setName(name);
			newContent.setPublishTime(StandardUtil.now());
			newContent.setLength(length);
			newContent.setLyrics(lyrics);
		
			musicContentDao.persist(newContent);

			transactionManager.commit(txstat);

		} catch(Exception ex) {
			ex.printStackTrace();
			System.err.println("DELETE FILE");
			outputFile.delete();
			try {
				transactionManager.rollback(txstat);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		return newContent;
		
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
			
		java.util.Date length = null;
		
		int contentType = findContentType(contentPath);
		
		if(contentType == -1){
			throw new RuntimeException("Unknown content type.");
		}
		
		DurationAndExtraInfo d;
		
		try{
			if(contentType == 1){
				d = findYoutubeVideoDuration(contentPath);
			}else{
				d = findSoundcloudTrackDuration(contentPath);
			}
			
		}catch(Exception ex){
			throw new RuntimeException("Could not find content duration.", ex);
		}
		
		length = d.duration;
		String extraInfo = d.extraInfo;
		
		MusicContent newContent = fillNewContentWithData(
				name,
				artist,
				genre,
				lyrics,
				contentPath,
				publisherId,
				length,
				contentType,
				StandardUtil.now(),
				extraInfo
		);
		
		musicContentDao.persist(newContent);
			
		return newContent;
	}
	
	@Transactional
	private MusicContent fillNewContentWithData(
			String name,
			String artist,
			String genre,
			String lyrics,
			String contentPath,
			int publisherId,
			java.util.Date length,
			int contentType,
			java.util.Date publishTime,
			String extraInfo
			){
		MusicContent newContent =  new MusicContent();
		User user = userDao.findById(publisherId);
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
		newContent.setPublishTime(publishTime);
		newContent.setLength(length);
		newContent.setLyrics(lyrics);
		newContent.setExtraInfo(extraInfo);
		
		return newContent;
		
	}
	
	/**
	 * 
	 * @param contentPath url link to content
	 * @return 1 if it's youtube, 2 if it's soundcloud, -1 otherwise
	 */
	protected int findContentType(String contentPath){
		if(contentPath.matches("http(s)?://(www\\.)?youtube\\.com/.+")){
			return 1;
		}else if(contentPath.matches("http(s)?://(www\\.)?soundcloud\\.com/.+")){
			return 2;
		}else{
			return -1;
		}
	}
	
	protected DurationAndExtraInfo findSoundcloudTrackDuration(String trackUrl) throws Exception {
		String requestUrl = soundcloudApiRequestTemplate.replace("<<TRACK_URL>>", URLEncoder.encode(trackUrl, "UTF-8"));
		
		JSONObject jsonObj = fetchJsonObjectFromUrl(requestUrl);
		
		long durationMs = jsonObj.getLong("duration");
		
		DurationAndExtraInfo ret = new DurationAndExtraInfo();
		
		LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(durationMs), ZoneId.ofOffset("UTC", ZoneOffset.ofHours(0)));
		ret.duration =  StandardUtil.fromLocalDateTime(ldt);
		ret.extraInfo = String.valueOf(jsonObj.getLong("id"));	

		return ret;
		
	}
	
	protected DurationAndExtraInfo findYoutubeVideoDuration(String contentPath) throws Exception{
		
		
		String videoId = extractYoutubeVideoId(contentPath);
		
		String requrlstr = youtubeApiv3RequestTemplate.replace("<<VIDEO_ID>>", videoId);
		
		JSONObject jsonObj = fetchJsonObjectFromUrl(requrlstr);
		String durationString =	jsonObj.getJSONArray("items")
				.getJSONObject(0)
				.getJSONObject("contentDetails")
				.getString("duration");
		
		java.util.Date duration = parseYoutubeDurationString(durationString);
		
		DurationAndExtraInfo ret = new DurationAndExtraInfo();
		ret.duration = duration;
		ret.extraInfo = videoId;
		return ret;
	}
	
	private JSONObject fetchJsonObjectFromUrl(String url) throws Exception{
		URL reqUrl = new URL(url);
		
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(reqUrl.openStream()));
		
		StringBuilder sb = new StringBuilder();
		String line = null;
		
		while((line = reader.readLine())!=null){
			sb.append(line);
		}
		
		JSONObject jsonObj = new JSONObject(sb.toString());
		
		return jsonObj;
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
	
	public MusicContent findMusicContentById(int id){
		MusicContent mc = musicContentDao.findById(id);
		return mc.isActive() ? mc : null;
	}
	
	/**
	 * Search content in DB. All parameters can be empty or null, in which case they will be ignored
	 * @param namePart part of music content name to be found
	 * @param artistPart part of artist's name
	 * @param genrePart part of genre's name
	 * @return list of content that matches criteria
	 */
	public List<MusicContent> searchForMusicContent(
			String namePart,
			String artistPart,
			String genrePart
	){
		
		namePart = (namePart == null || namePart.isEmpty()) ? null : namePart;
		artistPart = (artistPart == null || artistPart.isEmpty()) ? null : artistPart;
		genrePart = (genrePart == null || genrePart.isEmpty()) ? null : genrePart;
				
		return musicContentDao.search(namePart, artistPart, genrePart);
	}
	
	private static class DurationAndExtraInfo {
		public java.util.Date duration;
		public String extraInfo;
	}
}
