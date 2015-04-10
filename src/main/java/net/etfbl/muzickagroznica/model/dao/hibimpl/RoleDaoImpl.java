package net.etfbl.muzickagroznica.model.dao.hibimpl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.etfbl.muzickagroznica.model.dao.RoleDao;
import net.etfbl.muzickagroznica.model.entities.Role;

import java.util.List;

@Component
@Transactional
public class RoleDaoImpl implements RoleDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public RoleDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Role role) {
		sessionFactory.getCurrentSession().persist(role);

	}

	@Override
	public Role merge(Role role) {
		return (Role)sessionFactory.getCurrentSession().merge(role);
	}

	@Override
	public void delete(Role role) {
		sessionFactory.getCurrentSession().delete(role);

	}

	@Override
	public Role findById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("FROM Role WHERE id = :id");
		q.setParameter("id", id);
		return (Role) q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findByExample(Role role) {
		List<Role> results = (List<Role>)sessionFactory
				.getCurrentSession()
				.createCriteria(
						"net.etfbl.muzickagroznica.model.entities.Role")
				.add(Example.create(role)).list();

		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findByUserId(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("SELECT r FROM Role r WHERE r.id.userId = :id");
		q.setParameter("id", id);
		return (List<Role>) q.list();
	}

}
