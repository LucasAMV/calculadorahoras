package br.com.emtest.persistence.dao;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.emtest.persistence.entities.Rental;

/**
 * Home object for domain model class Rental.
 * @see .Rental
 * @author Hibernate Tools
 */
@AutoCreate
@Name("rentalDAO")
@Scope(ScopeType.SESSION)
public class RentalDAO {

	private static final Log log = LogFactory.getLog(RentalDAO.class);

	@In
	private EntityManager entityManager;

	public void persist(Rental transientInstance) {
		log.debug("persisting Rental instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Rental persistentInstance) {
		log.debug("removing Rental instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Rental merge(Rental detachedInstance) {
		log.debug("merging Rental instance");
		try {
			Rental result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Rental findById(Integer id) {
		log.debug("getting Rental instance with id: " + id);
		try {
			Rental instance = entityManager.find(Rental.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
