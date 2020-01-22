// default package
// Generated 21/01/2020 23:08:10 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Store.
 * @see .Store
 * @author Hibernate Tools
 */
@Stateless
public class StoreHome {

	private static final Log log = LogFactory.getLog(StoreHome.class);

	@PersistenceContext
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
