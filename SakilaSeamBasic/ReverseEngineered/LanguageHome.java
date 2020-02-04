// default package
// Generated 21/01/2020 23:08:10 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Language.
 * @see .Language
 * @author Hibernate Tools
 */
@Stateless
public class LanguageHome {

	private static final Log log = LogFactory.getLog(LanguageHome.class);

	@PersistenceContext
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
