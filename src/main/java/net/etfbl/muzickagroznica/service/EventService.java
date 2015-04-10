package net.etfbl.muzickagroznica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.etfbl.muzickagroznica.gateway.EmailGateway;
import net.etfbl.muzickagroznica.model.dao.EventDao;
import net.etfbl.muzickagroznica.model.dao.UserDao;
import net.etfbl.muzickagroznica.model.entities.Event;
import net.etfbl.muzickagroznica.model.entities.User;
import net.etfbl.muzickagroznica.util.StandardUtil;

@Service
public class EventService {

	@Autowired
	EventDao eventDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	EmailGateway emailGateway;
	
	public EventService() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	public Event addNewEvent(
			String name,
			String description,
			String location,
			java.util.Date eventTime,
			int userId
	){
		Event e = new Event();
		
		
		
		e.setApprovalStatus(0);
		e.setDescription(description);
		e.setEventTime(eventTime);
		e.setLocation(location);
		e.setName(name);
		e.setPublishTime(StandardUtil.now());
		
		User user = userDao.findById(userId);
		e.setUser(user);
		
		eventDao.persist(e);
		
		return e;
		
	}
	
	@Transactional
	public Event findEventById(int eventId){
		return eventDao.findById(eventId);
	}
	
	@Transactional
	public List<Event> findEventsPendingApproval(){
		Event pattern = new Event();
		pattern.setApprovalStatus(0);
		List<Event> results = eventDao.findByExample(pattern);
		return results;
	}
	
	/**
	 * 
	 * @param eventId id of event to approve/disapprove
	 * @param approve if true event will be approved, otherwise it will be disapproved
	 * @return event
	 */
	@Transactional
	public Event approveEvent(int eventId, boolean approve){
		Event event = eventDao.findById(eventId);
		
		if(event == null || event.getApprovalStatus() != 0){
			return null;
		}
		
		int approvalStatus = -1;
		if(approve){
			approvalStatus += 2;
		}
		
		event.setApprovalStatus(approvalStatus);
		
		event =  eventDao.merge(event);
		
		List<User> users = userDao.findByActive(true);
		String subject = event.getName();
		StringBuilder textBuilder = new StringBuilder(event.getName());
		textBuilder.append("\n\r");
		textBuilder.append(event.getLocation());
		textBuilder.append("\n\r");
		textBuilder.append(event.getEventTime());
		textBuilder.append("\n\r");
		textBuilder.append(event.getDescription());
		textBuilder.append("\n\r");
		String text = textBuilder.toString();
		
		
		emailGateway.sendEmailsToUsers(users, subject, text);
		
		return event;

	}
}
