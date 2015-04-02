package net.etfbl.muzickagroznica.service;

import net.etfbl.muzickagroznica.model.dao.ArtistDao;
import net.etfbl.muzickagroznica.model.dao.CommentDao;
import net.etfbl.muzickagroznica.model.dao.FavoriteDao;
import net.etfbl.muzickagroznica.model.dao.GenreDao;
import net.etfbl.muzickagroznica.model.dao.ListeningDao;
import net.etfbl.muzickagroznica.model.dao.MusicContentDao;
import net.etfbl.muzickagroznica.model.dao.PlaylistDao;
import net.etfbl.muzickagroznica.model.dao.RateDao;
import net.etfbl.muzickagroznica.model.dao.UserDao;
import net.etfbl.muzickagroznica.model.entities.Artist;
import net.etfbl.muzickagroznica.model.entities.Comment;
import net.etfbl.muzickagroznica.model.entities.Favorite;
import net.etfbl.muzickagroznica.model.entities.Genre;
import net.etfbl.muzickagroznica.model.entities.Listening;
import net.etfbl.muzickagroznica.model.entities.MusicContent;
import net.etfbl.muzickagroznica.model.entities.Playlist;
import net.etfbl.muzickagroznica.model.entities.Rate;
import net.etfbl.muzickagroznica.model.entities.User;
import net.etfbl.muzickagroznica.service.helper.entities.PlaylistSummaryData;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

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
	FavoriteDao favoriteDao;
	
	@Autowired
	RateDao rateDao;
	
	@Autowired
	CommentDao commentDao;
	
	@Autowired
	ListeningDao listeningDao;
	
	@Autowired
	PlaylistDao playlistDao;
	
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
		
		long millisDuration;
		try {
			
			//Ad-hoc quick solution
			//first try with mp3spi's MpegAudioFileReader, and if it throws Unsupp...
			//then we try AudioSystem
			
			try {
				AudioFileFormat baseFileFormat = new MpegAudioFileReader().getAudioFileFormat(outputFile);
				Map<String, Object> properties = baseFileFormat.properties();
				millisDuration = ((Long) properties.get("duration"))/1000;
			}catch(UnsupportedAudioFileException uafe){
				AudioFileFormat aff = AudioSystem.getAudioFileFormat(outputFile);
				Object o = aff.getProperty("duration");
				millisDuration = ((Long) o)/1000;
			}

			
		} catch (Exception ex){
			throw new RuntimeException("Could not get audio file duration.", ex);
		}
		
		java.util.Date length = new java.util.Date(millisDuration);
		
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
			newContent.setDuration(length);
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
		newContent.setDuration(length);
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
		
		ret.duration =  new java.util.Date(durationMs);
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
		Duration duration = Duration.parse(durationString);
		return new java.util.Date(duration.toMillis());
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
				
		List<MusicContent> result = musicContentDao.search(namePart, artistPart, genrePart);
		result.removeIf(new Predicate<MusicContent>() {

			@Override
			public boolean test(MusicContent t) {
				return !t.isActive();
			}
			
		});
		
		return result;
	}
	
	@Transactional
	public Favorite removeFromFavorites(int userId, int musicContentId){
		Favorite favorite = findFavorite(userId, musicContentId);
		
		if(favorite == null){
			return favorite;
		}
		
		favoriteDao.delete(favorite);
		
		return favorite;
		
	}
	
	
	@Transactional
	public Favorite addToFavorites(int userId, int musicContentId){
		
		User user = userDao.findById(userId);
		MusicContent mc = musicContentDao.findById(musicContentId);
		
		if(!mc.isActive()){
			return null;
		}
		
		Favorite favorite = new Favorite();
		favorite.setFavoritingTime(StandardUtil.now());
		favorite.setMusicContent(mc);
		favorite.setUser(user);

		user.getFavorites().add(favorite);
		mc.getFavorites().add(favorite);
		
		favoriteDao.persist(favorite);
		
		return favorite;
		
	}
	
	@Transactional
	public Favorite findFavorite(int userId, int musicContentId){
		Favorite favorite = new Favorite();
		favorite.setMusicContentId(musicContentId);
		favorite.setUserId(userId);
		List<Favorite> favorites = favoriteDao.findByExample(favorite);
		
		if(favorites.size() == 0){
			return null;
		}else{
			return favorites.get(0);
		}
	}
	
	@Transactional
	public Rate findRate(int userId, int musicContentId){
		Rate rate = new Rate();
		rate.setMusicContentId(musicContentId);
		rate.setUserId(userId);
		
		List<Rate> rates = rateDao.findByExample(rate);
		
		if(rates.size() == 0){
			return null;
		} else {
			return rates.get(0);
		}
		
	}
	
	@Transactional
	public Rate rate(int userId, int musicContentId, int value){
		Rate rate = findRate(userId, musicContentId);
		if(rate == null){
			return rateNew(userId, musicContentId, value);
		}else{
			return changeExistingRate(rate, value);
		}
	}
	
	@Transactional
	private Rate rateNew(int userId, int musicContentId, int value){
		User user = userDao.findById(userId);
		MusicContent mc = musicContentDao.findById(musicContentId);
		Rate rate = new Rate();
		rate.setMusicContent(mc);
		rate.setUser(user);
		rate.setRatingTime(StandardUtil.now());
		rate.setRate(value);
		
		rateDao.persist(rate);
		
		return rate;
	}
	
	@Transactional
	private Rate changeExistingRate(Rate rate, int value){
		rate.setRate(value);
		rate.setRatingTime(StandardUtil.now());
		return rate;
	}
	
	@Transactional
	public Comment addComment(int userId, int musicContentId, String commentText){
		User user = userDao.findById(userId);
		MusicContent mc = musicContentDao.findById(musicContentId);
		Comment comment = new Comment();
		comment.setCommentingTime(StandardUtil.now());
		comment.setCommentText(commentText);
		comment.setMusicContent(mc);
		comment.setUser(user);
		commentDao.persist(comment);
		return comment;
	}
	
	@Transactional
	public List<Comment> listComments(int musicContentId){
		return commentDao.findForMusicContent(musicContentId);
	}
	
	@Transactional
	public boolean deleteComment(int commentId){
		Comment comment = commentDao.findById(commentId);
		if(comment == null){
			return false;
		}
		commentDao.delete(comment);
		return true;
	}
	
	@Transactional
	public Listening recordListening(int userId, int musicContentId){
		Listening listening = new Listening();
		MusicContent musicContent = musicContentDao.findById(musicContentId);
		if(musicContent == null){
			return null;
		}
		
		User user = userDao.findById(userId);
		if(user == null){
			return null;
		}
		
		listening.setListeningTime(StandardUtil.now());
		listening.setMusicContent(musicContent);
		listening.setUser(user);
		
		listeningDao.persist(listening);
		
		
		return listening;
	}
	
	/**
	 * 
	 * @param userId creator Id
	 * @param title title of playlist
	 * @param initialContentId if negative it will be ignored
	 * @return
	 */
	@Transactional
	public Playlist createPlaylist(int userId, String title, int initialContentId){
		User user = userDao.findById(userId);
		Playlist pl = new Playlist();
		pl.setUser(user);
		pl.setCreationTime(StandardUtil.now());
		pl.setTitle(title);
		
		playlistDao.persist(pl);
		
		MusicContent mc = musicContentDao.findById(initialContentId);
		
		pl.getMusicContents().add(mc);
		
		return pl;
	}
	
	@Transactional
	public Playlist addToPlaylist(int playlistId, int musicContentId){
		Playlist playlist = playlistDao.findById(playlistId);
		MusicContent musicContent = musicContentDao.findById(musicContentId);
		playlist.getMusicContents().add(musicContent);
		
		return playlist;
	}
	
	@Transactional
	public List<Playlist> usersPlaylists(int userId){
		//TODO: this can be improved if I change Playlist.hbm.xml
		Playlist playlist = new Playlist();
		
		playlist.setUser(userDao.findById(userId));
		
		return playlistDao.findByExample(playlist);
	}
	
	/**
	 * 	 * 
	 * @param userId id of user who's playlists you want
	 * @param musicContentId id of music content 
	 * @return List of Playlists that belong to user with id userId,
	 * 	and do not contain music content with id musicContentId
	 */
	@Transactional
	public List<Playlist> usersPlaylists(int userId, final int musicContentId){
		List<Playlist> ret = usersPlaylists(userId);
		
		ret.removeIf(new Predicate<Playlist>() {

			@Override
			public boolean test(Playlist t) {
				for(MusicContent mc : t.getMusicContents()){
					if(mc.getId() == musicContentId){
						return true;
					}
				}
				return false;
			}
			
		});
		
		return ret;
	}
	
	@Transactional
	public List<PlaylistSummaryData> usersPlaylistSummaryData(int userId){
		List<Playlist> playlists = usersPlaylists(userId);
		List<PlaylistSummaryData> ret = new ArrayList<PlaylistSummaryData>(playlists.size());
		for(Playlist pl : playlists){
			PlaylistSummaryData pd = new PlaylistSummaryData();
			pd.setCreationTime(pl.getCreationTime());
			pd.setId(pl.getId());
			pd.setTitle(pl.getTitle());
			pd.setNumberOfContents(pl.getMusicContents().size());
			ret.add(pd);
		}
		return ret;
	}
	
	@Transactional
	public List<MusicContent> musicContentForPlaylist(int playlistId){
		return new ArrayList<MusicContent>(playlistDao.findById(playlistId).getMusicContents());
	}
	
	@Transactional
	public Playlist findPlaylist(int playlistId){
		return playlistDao.findById(playlistId);
	}
	
	private static class DurationAndExtraInfo {
		public java.util.Date duration;
		public String extraInfo;
	}
}
