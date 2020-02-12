package br.com.emtest.persistence.dao;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.emtest.persistence.entities.Staff;

/**
 * Home object for domain model class Staff.
 * @see .Staff
 * @author Hibernate Tools
 */
@AutoCreate
@Name("staffDAO")
@Scope(ScopeType.SESSION)
public class StaffDAO {

	private static final Log log = LogFactory.getLog(StaffDAO.class);

	@In
	private EntityManager entityManager;

	public void persist(Staff transientInstance) {
		log.debug("persisting Staff instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Staff persistentInstance) {
		log.debug("removing Staff instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Staff merge(Staff detachedInstance) {
		log.debug("merging Staff instance");
		try {
			Staff result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Staff findById(Byte id) {
		log.debug("getting Staff instance with id: " + id);
		try {
			Staff instance = entityManager.find(Staff.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
