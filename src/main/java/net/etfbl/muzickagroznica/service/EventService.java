package net.etfbl.muzickagroznica.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	SessionFactory sf;
	
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
	
}
