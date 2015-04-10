package net.etfbl.muzickagroznica.model.dao.hibimpl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.etfbl.muzickagroznica.model.dao.FavoriteDao;
import net.etfbl.muzickagroznica.model.entities.Favorite;

import java.util.List;

@Component
@Transactional
public class FavoriteDaoImpl implements FavoriteDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public FavoriteDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Favorite favorite) {
		sessionFactory.getCurrentSession().persist(favorite);

	}

	@Override
	public Favorite merge(Favorite favorite) {
		return (Favorite)sessionFactory.getCurrentSession().merge(favorite);
	}

	@Override
	public void delete(Favorite favorite) {
		sessionFactory.getCurrentSession().delete(favorite);

	}

	@Override
	public Favorite findById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("FROM Favorite WHERE id = :id");
		q.setParameter("id", id);
		return (Favorite) q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Favorite> findByExample(Favorite favorite) {
		List<Favorite> results = (List<Favorite>)sessionFactory
				.getCurrentSession()
				.createCriteria(
						"net.etfbl.muzickagroznica.model.entities.Favorite")
				.add(Example.create(favorite)).list();

		return results;
	}

}
