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

import net.etfbl.muzickagroznica.model.dao.UserDao;
import net.etfbl.muzickagroznica.model.entities.User;

import java.util.Date;
import java.util.List;

@Component
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(User user) {
		sessionFactory.getCurrentSession().persist(user);
	}

	@Override
	public User merge(User user) {
		return (User)sessionFactory.getCurrentSession().merge(user);
	}

	@Override
	public void delete(User user) {
		sessionFactory.getCurrentSession().delete(user);

	}

	@Override
	public User findById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("FROM User WHERE id = :id");
		q.setParameter("id", id);
		q.setCacheable(true);
		return (User) q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByExample(User user) {
		List<User> results = (List<User>)sessionFactory
				.getCurrentSession()
				.createCriteria(
						"net.etfbl.muzickagroznica.model.entities.User")
				.add(Example.create(user))
				.setCacheable(true)
				.list();

		return results;
	}
	
	@Override
	public User findByUsername(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("FROM User WHERE username = :username");
		q.setParameter("username", username);
		return (User) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByActive(boolean active) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(User.class);
		cr.add(Restrictions.eq("active", active));
		
		return (List<User>) cr.list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findRegisteredAfter(Date date) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(User.class);
		cr.add(Restrictions.ge("registrationTime", date));
		
		return (List<User>) cr.list();
	}
	
	

}
