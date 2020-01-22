// default package
// Generated 21/01/2020 23:08:10 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class NicerButSlowerFilmList.
 * @see .NicerButSlowerFilmList
 * @author Hibernate Tools
 */
@Stateless
public class NicerButSlowerFilmListHome {

	private static final Log log = LogFactory.getLog(NicerButSlowerFilmListHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(NicerButSlowerFilmList transientInstance) {
		log.debug("persisting NicerButSlowerFilmList instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(NicerButSlowerFilmList persistentInstance) {
		log.debug("removing NicerButSlowerFilmList instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public NicerButSlowerFilmList merge(NicerButSlowerFilmList detachedInstance) {
		log.debug("merging NicerButSlowerFilmList instance");
		try {
			NicerButSlowerFilmList result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public NicerButSlowerFilmList findById(NicerButSlowerFilmListId id) {
		log.debug("getting NicerButSlowerFilmList instance with id: " + id);
		try {
			NicerButSlowerFilmList instance = entityManager.find(NicerButSlowerFilmList.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
