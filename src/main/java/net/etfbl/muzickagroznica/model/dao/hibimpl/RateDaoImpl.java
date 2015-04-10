package net.etfbl.muzickagroznica.model.dao.hibimpl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.etfbl.muzickagroznica.model.dao.RateDao;
import net.etfbl.muzickagroznica.model.entities.Rate;

import java.util.List;

@Component
@Transactional
public class RateDaoImpl implements RateDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public RateDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Rate rate) {
		sessionFactory.getCurrentSession().persist(rate);

	}

	@Override
	public Rate merge(Rate rate) {
		return (Rate)sessionFactory.getCurrentSession().merge(rate);
	}

	@Override
	public void delete(Rate rate) {
		sessionFactory.getCurrentSession().delete(rate);

	}

	@Override
	public Rate findById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("FROM Rate WHERE id = :id");
		q.setParameter("id", id);
		return (Rate) q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Rate> findByExample(Rate rate) {
		List<Rate> results = (List<Rate>)sessionFactory
				.getCurrentSession()
				.createCriteria(
						"net.etfbl.muzickagroznica.model.entities.Rate")
				.add(Example.create(rate)).list();

		return results;
	}

}
