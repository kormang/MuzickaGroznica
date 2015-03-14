package net.etfbl.muzickagroznica.model.dao.hibimpl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;

import net.etfbl.muzickagroznica.model.dao.PlaylistDao;
import net.etfbl.muzickagroznica.model.entities.Playlist;

import java.util.List;

public class PlaylistDaoImpl implements PlaylistDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public PlaylistDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Playlist playlist) {
		sessionFactory.getCurrentSession().persist(playlist);

	}

	@Override
	public Playlist merge(Playlist playlist) {
		return (Playlist)sessionFactory.getCurrentSession().merge(playlist);
	}

	@Override
	public void delete(Playlist playlist) {
		sessionFactory.getCurrentSession().delete(playlist);

	}

	@Override
	public Playlist findById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("FROM Playlist WHERE id = :id");
		q.setParameter("id", id);
		return (Playlist) q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Playlist> findByExample(Playlist playlist) {
		List<Playlist> results = (List<Playlist>)sessionFactory
				.getCurrentSession()
				.createCriteria(
						"net.etfbl.muzickagroznica.model.entities.Playlist")
				.add(Example.create(playlist)).list();

		return results;
	}

}
