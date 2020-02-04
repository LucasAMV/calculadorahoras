// default package
// Generated 21/01/2020 23:08:10 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ActorInfo.
 * @see .ActorInfo
 * @author Hibernate Tools
 */
@Stateless
public class ActorInfoHome {

	private static final Log log = LogFactory.getLog(ActorInfoHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ActorInfo transientInstance) {
		log.debug("persisting ActorInfo instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ActorInfo persistentInstance) {
		log.debug("removing ActorInfo instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ActorInfo merge(ActorInfo detachedInstance) {
		log.debug("merging ActorInfo instance");
		try {
			ActorInfo result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ActorInfo findById(ActorInfoId id) {
		log.debug("getting ActorInfo instance with id: " + id);
		try {
			ActorInfo instance = entityManager.find(ActorInfo.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
