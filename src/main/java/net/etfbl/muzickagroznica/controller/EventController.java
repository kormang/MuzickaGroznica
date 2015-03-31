package net.etfbl.muzickagroznica.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.etfbl.muzickagroznica.form.bean.NewEventForm;
import net.etfbl.muzickagroznica.model.entities.Event;
import net.etfbl.muzickagroznica.model.entities.User;
import net.etfbl.muzickagroznica.service.EventService;

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
public class EventController extends MuzickaGroznicaController {

	@Autowired
	EventService eventService;
	
	@Autowired
	MessageSource messageSource;
	
	public EventController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value="/super/event/new", method=RequestMethod.GET)
	public String viewEventNew(Map<String, Object> model){
		model.put("newEventForm",new NewEventForm());
		return "event/new";
	}
	
	@RequestMapping(value="/super/event/new", method=RequestMethod.POST)
	public String eventNew(
			@Valid @ModelAttribute("newEventForm") NewEventForm newEventForm,
			HttpSession session
	){

		User user = (User) session.getAttribute("user");
		Event event = eventService.addNewEvent(
				newEventForm.getName(),
				newEventForm.getDescription(),
				newEventForm.getLocation(),
				newEventForm.getEventTime(),
				user.getId()
		);
		
		
		return "redirect:/event/view/" + event.getId();
	}
	
	@RequestMapping(value="/event/view/{eventId}")
	public String eventView(
			@PathVariable("eventId") int eventId,
			Locale local,
			Map<String, Object> model
	){
		
		Event event = eventService.findEventById(eventId);
		
		DateFormat df = new SimpleDateFormat(messageSource.getMessage("muzickagroznica.dateTimeFormat", null, local));
		
		model.put("event", event);
		model.put("event_dateAndTime", df.format(event.getEventTime()));
		
		return "event/view";
	}
	
	@RequestMapping(value="/admin/pending_events")
	public String viewEventsPendingApproval(Map<String, Object> model){
		
		List<Event> events = eventService.findEventsPendingApproval();
		model.put("events", events);
		
		return "admin/pending_events";
	}
	
	@RequestMapping(value="/admin/event_approval", produces="application/json; charset=UTF-8")
	public @ResponseBody String eventApproval(
			@RequestParam("eid") int eid,
			@RequestParam("approve") boolean approve
	){
		Event event = eventService.approveEvent(eid, approve);
		boolean result = event != null;
		
		return "{\"result\":" +result + "}";
	}
}
