package br.com.emtest.persistence.dao;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.emtest.persistence.entities.Inventory;

/**
 * Home object for domain model class Inventory.
 * @see .Inventory
 * @author Hibernate Tools
 */
@AutoCreate
@Name("inventoryDAO")
@Scope(ScopeType.SESSION)
public class InventoryDAO {

	private static final Log log = LogFactory.getLog(InventoryDAO.class);

	@In
	private EntityManager entityManager;

	public void persist(Inventory transientInstance) {
		log.debug("persisting Inventory instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Inventory persistentInstance) {
		log.debug("removing Inventory instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Inventory merge(Inventory detachedInstance) {
		log.debug("merging Inventory instance");
		try {
			Inventory result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Inventory findById(Integer id) {
		log.debug("getting Inventory instance with id: " + id);
		try {
			Inventory instance = entityManager.find(Inventory.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
