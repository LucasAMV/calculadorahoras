package br.com.emtest.persistence.dao;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.emtest.persistence.entities.Language;

/**
 * Home object for domain model class Language.
 * @see .Language
 * @author Hibernate Tools
 */
@AutoCreate
@Name("languageDAO")
@Scope(ScopeType.SESSION)
public class LanguageDAO {

	private static final Log log = LogFactory.getLog(LanguageDAO.class);

	@In
	private EntityManager entityManager;

	public void persist(Language transientInstance) {
		log.debug("persisting Language instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Language persistentInstance) {
		log.debug("removing Language instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Language merge(Language detachedInstance) {
		log.debug("merging Language instance");
		try {
			Language result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Language findById(Byte id) {
		log.debug("getting Language instance with id: " + id);
		try {
			Language instance = entityManager.find(Language.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
