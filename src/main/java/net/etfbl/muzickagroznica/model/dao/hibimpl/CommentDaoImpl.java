package net.etfbl.muzickagroznica.model.dao.hibimpl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.etfbl.muzickagroznica.model.dao.CommentDao;
import net.etfbl.muzickagroznica.model.entities.Comment;

import java.util.List;

@Component
@Transactional
public class CommentDaoImpl implements CommentDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public CommentDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Comment comment) {
		sessionFactory.getCurrentSession().persist(comment);

	}

	@Override
	public Comment merge(Comment comment) {
		return (Comment)sessionFactory.getCurrentSession().merge(comment);
	}

	@Override
	public void delete(Comment comment) {
		sessionFactory.getCurrentSession().delete(comment);

	}

	@Override
	public Comment findById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("FROM Comment WHERE id = :id");
		q.setParameter("id", id);
		return (Comment) q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> findByExample(Comment comment) {
		List<Comment> results = (List<Comment>)sessionFactory
				.getCurrentSession()
				.createCriteria(
						"net.etfbl.muzickagroznica.model.entities.Comment")
				.add(Example.create(comment)).list();

		return results;
	}

}
