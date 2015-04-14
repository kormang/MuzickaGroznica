package net.etfbl.muzickagroznica.controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.etfbl.muzickagroznica.controller.utils.RequestParamsFinder;
import net.etfbl.muzickagroznica.form.bean.ChangeContentInfoForm;
import net.etfbl.muzickagroznica.form.bean.ContentNewForm;
import net.etfbl.muzickagroznica.form.bean.SearchForm;
import net.etfbl.muzickagroznica.model.entities.Comment;
import net.etfbl.muzickagroznica.model.entities.Favorite;
import net.etfbl.muzickagroznica.model.entities.Genre;
import net.etfbl.muzickagroznica.model.entities.MusicContent;
import net.etfbl.muzickagroznica.model.entities.Playlist;
import net.etfbl.muzickagroznica.model.entities.Rate;
import net.etfbl.muzickagroznica.model.entities.User;
import net.etfbl.muzickagroznica.service.ContentService;
import net.etfbl.muzickagroznica.service.helper.entities.PlaylistSummaryData;
import net.etfbl.muzickagroznica.util.StandardUtil;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

@Controller
public class ContentController extends MuzickaGroznicaController {
	
	private static String audioFileEmbeddTemplate = "<div id=\"player_holder\" style=\"display: inline-block; width: 658px;\"><img id=\"eqvimg\" src=\"<<CONTEXT_PATH>>/images/audio-player-header.jpg\"></img><audio controls autoplay id=\"player\" style=\"display: block; margin-left: auto; margin-right: auto; margin: 0 auto; width: 100%\"><source src=\"<<FILE_PATH>>\">X</audio></div>";
 	private static String soundcloudEmbeddTemplate = "<iframe width=\"50%\" height=\"225\" scrolling=\"no\" frameborder=\"no\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/<<TRACK_ID>>&amp;auto_play=true&amp;hide_related=false&amp;show_comments=true&amp;show_user=true&amp;show_reposts=false&amp;visual=true\"></iframe>";
	private static String youtubeEmbeddTemplate = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/<<VIDEO_ID>>?autoplay=1\" frameborder=\"0\" allowfullscreen></iframe>";

	
	@Autowired
	ContentService contentService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	RequestParamsFinder paramsFinder;

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
	
	@RequestMapping(value="/content/available_artists", produces="application/json; charset=UTF-8")
	public @ResponseBody String availableArtists(){
		
		String[] artists = contentService.findAllArtists();
		
		if(artists.length == 0){
			return "{\"artists\" : []}";
		}
		
		StringBuilder sb = new StringBuilder("{\"artists\" : [");
		sb.append('"');
		sb.append(artists[0]);
		sb.append('"');
		
		for(int i = 1; i < artists.length; i++){
			sb.append(", \"");
			sb.append(artists[i]);
			sb.append('"');
		}
		
		sb.append("]}");
		
		return sb.toString();
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
			BindingResult result,
			HttpSession session,
			Map<String, Object> model
			){
		
		
		if(result.hasErrors()){
			putGenresInModel(model);
			return "content/new";
		}
		
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
	
	@RequestMapping(value="/content/delete")
	public String deleteContent(
			@RequestParam("mcid") int id
	){
		contentService.deleteMusicContent(id);
		return "redirect:/content/my_content";
	}
	
	@RequestMapping(value="/content/view_edit", method=RequestMethod.POST)
	public String viewEditContentInfo(
			HttpServletRequest request,
			Map<String, Object> model
	){
		
		try{
			int mcid = paramsFinder.findIntParam(request, "mcid");
			MusicContent mc = contentService.findMusicContentById(mcid);
			ChangeContentInfoForm changeContentInfoForm = new ChangeContentInfoForm();
			
			changeContentInfoForm.setArtist(mc.getArtistName());
			changeContentInfoForm.setGenre(mc.getGenreName());
			changeContentInfoForm.setId(mc.getId());
			changeContentInfoForm.setName(mc.getName());
			changeContentInfoForm.setLyrics(mc.getLyrics());
			
			model.put("changeContentInfoForm", changeContentInfoForm);
			putGenresInModel(model);
			return "content/edit";
			
		}catch(Exception ex){
			throw new RuntimeException("Error displaying content edit form", ex);
		}
		
		
	}
	
	@RequestMapping(value="/content/edit", method=RequestMethod.POST)
	public String editContentInfo(
			@Valid @ModelAttribute("changeContentInfoForm") ChangeContentInfoForm changeContentInfoForm,
			BindingResult result,
			Map<String, Object> model
	){
		
		if(result.hasErrors()){
			putGenresInModel(model);
			return "content/view_edit";
		}
		
		contentService.editMusicContent(
				changeContentInfoForm.getId(),
				changeContentInfoForm.getName(),
				changeContentInfoForm.getArtist(),
				changeContentInfoForm.getGenre(),
				changeContentInfoForm.getLyrics()
		);
		
		
		return "redirect:/content/my_content";
	}
	
	@RequestMapping(value="content/my_content")
	public String viewMyContent(
			HttpSession session,
			Map<String, Object> model
	){
		User user = (User) session.getAttribute("user");
		List<MusicContent> contents = contentService.findMusicContentForUser(user.getId());
	
		model.put("contents", contents);
		
		return "content/my_content";
	}
	
	@RequestMapping(value="/content/embedcode")
	public String embedCode(
			Map<String, Object> model,
			HttpServletRequest request,
			@RequestParam("mcid") int mcid
	){
		MusicContent musicContent = contentService.findMusicContentById(mcid);
		
		model.put("contentType", musicContent.getContentType());
		
		switch (musicContent.getContentType()) {
		case 0:
			String url = request.getContextPath() + "/contents/" + musicContent.getContentPath();
			model.put("filePath", url);
			break;
		case 1:
			model.put("videoId", musicContent.getExtraInfo());
			break;
		case 2:
			model.put("trackId", musicContent.getExtraInfo());
			break;

		default:
			throw new RuntimeException("This should not happen, contentType is not in [0, 2]");
		}
		
		return "content/embed_code";
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
		
		int totalRating = contentService.calculateRatingForMusicContent(contentId);
		model.put("totalRating", totalRating);
		
		contentService.recordListening(user.getId(), contentId);
		
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
	
	@RequestMapping(value="/content/add_to_playlist", produces="application/json; charset=UTF-8")
	public @ResponseBody String addToPlaylist(
			@RequestParam("plid") int playlistId,
			@RequestParam(value="pltitle", required=false) String newPlaylistTitle,
			@RequestParam("mcid") int musicContentId,
			HttpSession session
	){
		User user = (User)session.getAttribute("user");
		boolean result;
		
		if(playlistId < 0){
			System.err.println("TITLE: " + newPlaylistTitle);
			result = null != contentService.createPlaylist(user.getId(), newPlaylistTitle, musicContentId);
		} else {
			result = null != contentService.addToPlaylist(playlistId, musicContentId);
		}
		
		return "{ \"result\":"+result+"}";
	}
	
	@RequestMapping(value="/content/playlists")
	public String playlists(
			@RequestParam(value="mcid", required=false) Integer mcid,
			Map<String, Object> model,
			HttpSession session
	){
		User user = (User)session.getAttribute("user");
		
		List<Playlist> playlists;
		if(mcid == null){
			playlists = contentService.usersPlaylists(user.getId());
		} else {
			playlists = contentService.usersPlaylists(user.getId(), mcid);
		}
		
	
		model.put("playlists", playlists);
		
		return "content/playlists";
	}
	
	@RequestMapping(value="/content/view_user_playlists")
	public String viewUserPlaylists(
			HttpSession session,
			Map<String, Object> model
	){
		User user = (User) session.getAttribute("user");
		
		List<PlaylistSummaryData> playlists = contentService.usersPlaylistSummaryData(user.getId());
				
		model.put("playlists", playlists);
		
		
		return "content/view_user_playlists";
	}
	
	@RequestMapping(value="/content/view_playlist_content")
	public String viewPlaylistContent(
			@RequestParam("plid") int plid,
			Map<String, Object> model
	){
		List<MusicContent> contents = contentService.musicContentForPlaylist(plid);
		Playlist playlist = contentService.findPlaylist(plid);
		model.put("playlist", playlist);
		model.put("contents", contents);
		
		return "content/view_playlist_content";
	}
	
	@RequestMapping(value="/content/favorites")
	public String viewFavorites(HttpSession session, Map<String, Object> model){
		
		User user = (User) session.getAttribute("user");
		
		List<MusicContent> contents = contentService.findFavoriteMusicContentForUser(user.getId());
		
		model.put("contents", contents);
		
		return "content/view_favorites";
	}
	
	@RequestMapping(value="/content/top_rss_feed", produces="application/xml; charset=UTF-8")
	public @ResponseBody String rssTopContent(HttpServletRequest request){
		SyndFeed feed = new SyndFeedImpl();
		
		String surl = request.getRequestURL().toString();
		String listenSUrl = surl.substring(0, surl.lastIndexOf('/') + 1) + "listen/";
		URL url;
		try{
			url = new URL(surl);
		}catch(Exception ex){
			//shouldn't happend
			ex.printStackTrace();
		}
		
		
		feed.setFeedType("rss_2.0");
		feed.setTitle("muzickagroznica - top list");
		feed.setLink(surl);
		feed.setDescription("muzickagroznica - top list");
		
		List<MusicContent> tops = contentService.findTopNMusicContent(5);
		List<SyndEntry> entries = new ArrayList<>(5);
		
		for(MusicContent mc : tops){
			System.err.println(mc.getName());
			SyndEntry entry = new SyndEntryImpl();
			SyndContent description = new SyndContentImpl();
			
			
			entry.setTitle(mc.getName() + " - " + mc.getArtistName());
			entry.setLink(listenSUrl + mc.getId());
			entry.setPublishedDate(StandardUtil.now());
			
			description.setType("text/plain");
			description.setValue(mc.getLyrics().length() > 50 ? mc.getLyrics().substring(0, 50) : mc.getLyrics() );
			
			entry.setDescription(description);
			
			entries.add(entry);			
		}
		
		feed.setEntries(entries);
		
		SyndFeedOutput output = new SyndFeedOutput();
		try {
			return output.outputString(feed, true);
		} catch (FeedException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Failed to output feed.", e);
		}
		
		
	}
	
}