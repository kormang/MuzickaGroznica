package net.etfbl.muzickagroznica.model.dao.hibimpl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.etfbl.muzickagroznica.model.dao.ListeningDao;
import net.etfbl.muzickagroznica.model.entities.Listening;

import java.util.List;

@Component
@Transactional
public class ListeningDaoImpl implements ListeningDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public ListeningDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Listening listening) {
		sessionFactory.getCurrentSession().persist(listening);

	}

	@Override
	public Listening merge(Listening listening) {
		return (Listening)sessionFactory.getCurrentSession().merge(listening);
	}

	@Override
	public void delete(Listening listening) {
		sessionFactory.getCurrentSession().delete(listening);

	}

	@Override
	public Listening findById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("FROM Listening WHERE id = :id");
		q.setParameter("id", id);
		q.setCacheable(true);
		return (Listening) q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Listening> findByExample(Listening listening) {
		List<Listening> results = (List<Listening>)sessionFactory
				.getCurrentSession()
				.createCriteria(
						"net.etfbl.muzickagroznica.model.entities.Listening")
				.add(Example.create(listening))
				.setCacheable(true)
				.list();

		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Listening> findLastListening(int limit) {
		String queryString = "SELECT l FROM Listening l ORDER BY l.listeningTime DESC ";
		
		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		query.setMaxResults(limit);
		
		return (List<Listening>) query.list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Listening> findLastListening(int limit, int userId) {
		String queryString = "SELECT l FROM Listening l WHERE l.userId = :userId ORDER BY l.listeningTime DESC ";

		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		query.setMaxResults(limit);
		query.setParameter("userId", userId);
		
		return (List<Listening>) query.list();
		
	}

}
