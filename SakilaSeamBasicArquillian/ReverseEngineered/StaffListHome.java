// default package
// Generated 21/01/2020 23:08:10 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class StaffList.
 * @see .StaffList
 * @author Hibernate Tools
 */
@Stateless
public class StaffListHome {

	private static final Log log = LogFactory.getLog(StaffListHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(StaffList transientInstance) {
		log.debug("persisting StaffList instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(StaffList persistentInstance) {
		log.debug("removing StaffList instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public StaffList merge(StaffList detachedInstance) {
		log.debug("merging StaffList instance");
		try {
			StaffList result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public StaffList findById(StaffListId id) {
		log.debug("getting StaffList instance with id: " + id);
		try {
			StaffList instance = entityManager.find(StaffList.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
