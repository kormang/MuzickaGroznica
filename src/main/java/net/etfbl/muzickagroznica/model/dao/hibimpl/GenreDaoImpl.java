package net.etfbl.muzickagroznica.model.dao.hibimpl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.etfbl.muzickagroznica.model.dao.GenreDao;
import net.etfbl.muzickagroznica.model.entities.Genre;

import java.util.List;

@Component
@Transactional
public class GenreDaoImpl implements GenreDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public GenreDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Genre genre) {
		sessionFactory.getCurrentSession().persist(genre);

	}

	@Override
	public Genre merge(Genre genre) {
		return (Genre)sessionFactory.getCurrentSession().merge(genre);
	}

	@Override
	public void delete(Genre genre) {
		sessionFactory.getCurrentSession().delete(genre);

	}

	@Override
	public Genre findById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("FROM Genre WHERE id = :id");
		q.setParameter("id", id);
		return (Genre) q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Genre> findByExample(Genre genre) {
		List<Genre> results = (List<Genre>)sessionFactory
				.getCurrentSession()
				.createCriteria(
						"net.etfbl.muzickagroznica.model.entities.Genre")
				.add(Example.create(genre)).list();

		return results;
	}

}
