package br.com.emtest.persistence.dao;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.emtest.persistence.entities.Country;

/**
 * Home object for domain model class Country.
 * @see .Country
 * @author Hibernate Tools
 */
@AutoCreate
@Name("countryDAO")
@Scope(ScopeType.APPLICATION)
public class CountryDAO {

	private static final Log log = LogFactory.getLog(CountryDAO.class);

	@In
	private EntityManager entityManager;

	public void persist(Country transientInstance) {
		log.debug("persisting Country instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Country persistentInstance) {
		log.debug("removing Country instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Country merge(Country detachedInstance) {
		log.debug("merging Country instance");
		try {
			Country result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Country findById(Short id) {
		log.debug("getting Country instance with id: " + id);
		try {
			Country instance = entityManager.find(Country.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
