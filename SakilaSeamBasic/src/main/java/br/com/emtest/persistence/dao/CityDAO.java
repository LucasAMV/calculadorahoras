package br.com.emtest.persistence.dao;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.emtest.persistence.entities.City;

/**
 * Home object for domain model class City.
 * @see .City
 * @author Hibernate Tools
 */
@AutoCreate
@Name("cityDAO")
@Scope(ScopeType.SESSION)
public class CityDAO {

	private static final Log log = LogFactory.getLog(CityDAO.class);

	@In
	private EntityManager entityManager;

	public void persist(City transientInstance) {
		log.debug("persisting City instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(City persistentInstance) {
		log.debug("removing City instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public City merge(City detachedInstance) {
		log.debug("merging City instance");
		try {
			City result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public City findById(Short id) {
		log.debug("getting City instance with id: " + id);
		try {
			City instance = entityManager.find(City.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
