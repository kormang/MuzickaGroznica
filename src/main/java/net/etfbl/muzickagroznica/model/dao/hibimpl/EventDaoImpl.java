package net.etfbl.muzickagroznica.model.dao.hibimpl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.etfbl.muzickagroznica.model.dao.EventDao;
import net.etfbl.muzickagroznica.model.entities.Event;

import java.util.List;

@Component
@Transactional
public class EventDaoImpl implements EventDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public EventDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Event event) {
		sessionFactory.getCurrentSession().persist(event);

	}

	@Override
	public Event merge(Event event) {
		return (Event)sessionFactory.getCurrentSession().merge(event);
	}

	@Override
	public void delete(Event event) {
		sessionFactory.getCurrentSession().delete(event);

	}

	@Override
	public Event findById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("FROM Event WHERE id = :id");
		q.setParameter("id", id);
		return (Event) q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> findByExample(Event event) {
		List<Event> results = (List<Event>)sessionFactory
				.getCurrentSession()
				.createCriteria(
						"net.etfbl.muzickagroznica.model.entities.Event")
				.add(Example.create(event)).list();

		return results;
	}

}
