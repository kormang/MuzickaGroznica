package net.etfbl.muzickagroznica.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.etfbl.muzickagroznica.form.bean.ContentNewForm;
import net.etfbl.muzickagroznica.model.entities.Genre;
import net.etfbl.muzickagroznica.model.entities.MusicContent;
import net.etfbl.muzickagroznica.model.entities.User;
import net.etfbl.muzickagroznica.service.ContentService;
import net.etfbl.muzickagroznica.util.StandardUtilsBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {
	
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
		return "admin_genres";
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
		return "content_new";
	}
	
	@RequestMapping(value="/super/audio_file_upload", method=RequestMethod.GET)
	public String viewAudioFileUpload(Map<String, Object> model){
		putGenresInModel(model);
		return "super_audio_file_upload";
	}
	
	@RequestMapping(value="/super/audio_upload_error")
	public String viewAudioUploadError(){
		return "super_audio_upload_error";
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
		
		
		return "redirect:/content/listen/" + mc.getContentPath();
	}
}
