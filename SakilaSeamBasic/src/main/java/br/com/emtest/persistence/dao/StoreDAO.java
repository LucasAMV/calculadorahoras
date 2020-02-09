package br.com.emtest.persistence.dao;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.emtest.persistence.entities.Store;

/**
 * Home object for domain model class Store.
 * @see .Store
 * @author Hibernate Tools
 */
@AutoCreate
@Name("storeDAO")
@Scope(ScopeType.APPLICATION)
public class StoreDAO {

	private static final Log log = LogFactory.getLog(StoreDAO.class);

	@In
	private EntityManager entityManager;

	public void persist(Store transientInstance) {
		log.debug("persisting Store instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Store persistentInstance) {
		log.debug("removing Store instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Store merge(Store detachedInstance) {
		log.debug("merging Store instance");
		try {
			Store result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Store findById(Byte id) {
		log.debug("getting Store instance with id: " + id);
		try {
			Store instance = entityManager.find(Store.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
