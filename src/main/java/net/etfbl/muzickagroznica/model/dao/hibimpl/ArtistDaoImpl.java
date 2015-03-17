package net.etfbl.muzickagroznica.model.dao.hibimpl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.etfbl.muzickagroznica.model.dao.ArtistDao;
import net.etfbl.muzickagroznica.model.entities.Artist;

import java.util.List;

@Component
@Transactional
public class ArtistDaoImpl implements ArtistDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public ArtistDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Artist artist) {
		sessionFactory.getCurrentSession().persist(artist);

	}

	@Override
	public Artist merge(Artist artist) {
		return (Artist)sessionFactory.getCurrentSession().merge(artist);
	}

	@Override
	public void delete(Artist artist) {
		sessionFactory.getCurrentSession().delete(artist);

	}

	@Override
	public Artist findById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("FROM Artist WHERE id = :id");
		q.setParameter("id", id);
		return (Artist) q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Artist> findByExample(Artist artist) {
		List<Artist> results = (List<Artist>)sessionFactory
				.getCurrentSession()
				.createCriteria(
						"net.etfbl.muzickagroznica.model.entities.Artist")
				.add(Example.create(artist)).list();

		return results;
	}

}
