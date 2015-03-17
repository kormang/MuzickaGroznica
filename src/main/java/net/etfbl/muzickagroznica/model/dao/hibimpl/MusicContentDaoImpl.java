package net.etfbl.muzickagroznica.model.dao.hibimpl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.etfbl.muzickagroznica.model.dao.MusicContentDao;
import net.etfbl.muzickagroznica.model.entities.MusicContent;

import java.util.List;

@Component
@Transactional
public class MusicContentDaoImpl implements MusicContentDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public MusicContentDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(MusicContent musiccontent) {
		sessionFactory.getCurrentSession().persist(musiccontent);

	}

	@Override
	public MusicContent merge(MusicContent musiccontent) {
		return (MusicContent)sessionFactory.getCurrentSession().merge(musiccontent);
	}

	@Override
	public void delete(MusicContent musiccontent) {
		sessionFactory.getCurrentSession().delete(musiccontent);

	}

	@Override
	public MusicContent findById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("FROM MusicContent WHERE id = :id");
		q.setParameter("id", id);
		return (MusicContent) q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MusicContent> findByExample(MusicContent musiccontent) {
		List<MusicContent> results = (List<MusicContent>)sessionFactory
				.getCurrentSession()
				.createCriteria(
						"net.etfbl.muzickagroznica.model.entities.MusicContent")
				.add(Example.create(musiccontent)).list();

		return results;
	}

}
