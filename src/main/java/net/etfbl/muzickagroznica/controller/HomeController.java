package net.etfbl.muzickagroznica.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.etfbl.muzickagroznica.form.bean.SearchForm;
import net.etfbl.muzickagroznica.form.bean.UserForm;
import net.etfbl.muzickagroznica.model.dao.RoleDao;
import net.etfbl.muzickagroznica.model.entities.MusicContent;
import net.etfbl.muzickagroznica.model.entities.User;
import net.etfbl.muzickagroznica.service.ContentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends MuzickaGroznicaController {
	
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	ContentService contentService;
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static URL newsFeedUrl;
	
	static {
		 try {
			newsFeedUrl = new URL("http://www.rollingstone.com/music.rss");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws IOException 
	 * @throws FeedException 
	 * @throws IllegalArgumentException 
	 */
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request, HttpSession session) throws IllegalArgumentException, FeedException, IOException {
		//logger.info("Welcome home! The client locale is {}.", locale);
		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
		
//		model.addAttribute("serverTime", formattedDate );
		
		model.addAttribute("userForm", new UserForm());
		model.addAttribute("searchForm", new SearchForm());
		
		User user = (User) session.getAttribute("user");
		
		if(user != null){
			List<MusicContent> recommended = contentService.findRecomendedMusicContent(user.getId());
			model.addAttribute("recommended", recommended);
		}
		
		putNewsFeedInModel(model);
		
		return "home";
	}

	
	private void putNewsFeedInModel(Model model) throws IllegalArgumentException, FeedException, IOException{
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(newsFeedUrl));
		
		@SuppressWarnings("unchecked")
		List<SyndEntry> entries = feed.getEntries();
		model.addAttribute("feeds", entries);
		
	}

	
	
}
