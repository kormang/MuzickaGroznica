package net.etfbl.muzickagroznica.model.dao.hibimpl;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.etfbl.muzickagroznica.model.dao.MusicContentDao;
import net.etfbl.muzickagroznica.model.entities.MusicContent;

import java.util.ArrayList;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<MusicContent> search(String name, String artist, String genre) {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder queryStringBuilder = new StringBuilder("SELECT mc FROM MusicContent mc WHERE ");
		if(name == null && artist == null && genre == null){
			return new ArrayList<MusicContent>();
		}
		
		if(name != null){
			queryStringBuilder.append("mc.name LIKE :nameq ");
		}
		
		if(artist != null){
			if(name != null){
				queryStringBuilder.append(" AND ");
			}
			
			queryStringBuilder.append("mc.artist.name LIKE :artistq ");
		}
		
		if(genre != null){
			if(name != null || artist != null){
				queryStringBuilder.append(" AND ");
			}
			
			queryStringBuilder.append("mc.genre.name LIKE :genreq");			
		}

		String queryString = queryStringBuilder.toString();

		Query query = session.createQuery(queryString);
		
		if(name != null){
			query.setParameter("nameq", '%'+name+'%');
		}
		
		if(artist != null){
			query.setParameter("artistq", '%'+artist+'%');
		}
		
		if(genre != null){
			query.setParameter("genreq", '%'+genre+'%');
		}
		
		return (List<MusicContent>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MusicContent> random(int limit, String genreRestriction, String artistRestriction) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MusicContent.class);
		
		if(artistRestriction != null){
			criteria.add(Restrictions.eq("artistName", artistRestriction));
		}
		
		if(genreRestriction != null){
			criteria.add(Restrictions.eq("genreName", genreRestriction));
		}
		
		criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
		criteria.setMaxResults(limit);
		return criteria.list();
	}

}
