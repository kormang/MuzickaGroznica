package net.etfbl.muzickagroznica.model.dao.hibimpl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;

import net.etfbl.muzickagroznica.model.dao.ListeningDao;
import net.etfbl.muzickagroznica.model.entities.Listening;

import java.util.List;

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
		return (Listening) q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Listening> findByExample(Listening listening) {
		List<Listening> results = (List<Listening>)sessionFactory
				.getCurrentSession()
				.createCriteria(
						"net.etfbl.muzickagroznica.model.entities.Listening")
				.add(Example.create(listening)).list();

		return results;
	}

}
