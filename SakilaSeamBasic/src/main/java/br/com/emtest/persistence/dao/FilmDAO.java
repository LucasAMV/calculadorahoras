package br.com.emtest.persistence.dao;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.emtest.persistence.entities.Film;

/**
 * Home object for domain model class Film.
 * @see .Film
 * @author Hibernate Tools
 */
@AutoCreate
@Name("filmDAO")
@Scope(ScopeType.APPLICATION)
public class FilmDAO {

	private static final Log log = LogFactory.getLog(FilmDAO.class);

	@In
	private EntityManager entityManager;

	public void persist(Film transientInstance) {
		log.debug("persisting Film instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Film persistentInstance) {
		log.debug("removing Film instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Film merge(Film detachedInstance) {
		log.debug("merging Film instance");
		try {
			Film result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Film findById(Short id) {
		log.debug("getting Film instance with id: " + id);
		try {
			Film instance = entityManager.find(Film.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
