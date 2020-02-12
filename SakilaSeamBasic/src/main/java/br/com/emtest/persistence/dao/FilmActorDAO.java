package br.com.emtest.persistence.dao;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.emtest.persistence.entities.FilmActor;
import br.com.emtest.persistence.entities.embeddable.FilmActorId;

/**
 * Home object for domain model class FilmActor.
 * @see .FilmActor
 * @author Hibernate Tools
 */
@AutoCreate
@Name("filmActorDAO")
@Scope(ScopeType.SESSION)
public class FilmActorDAO {

	private static final Log log = LogFactory.getLog(FilmActorDAO.class);

	@In
	private EntityManager entityManager;

	public void persist(FilmActor transientInstance) {
		log.debug("persisting FilmActor instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(FilmActor persistentInstance) {
		log.debug("removing FilmActor instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public FilmActor merge(FilmActor detachedInstance) {
		log.debug("merging FilmActor instance");
		try {
			FilmActor result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public FilmActor findById(FilmActorId id) {
		log.debug("getting FilmActor instance with id: " + id);
		try {
			FilmActor instance = entityManager.find(FilmActor.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
