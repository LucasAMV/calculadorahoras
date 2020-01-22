// default package
// Generated 21/01/2020 23:08:10 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CustomerList.
 * @see .CustomerList
 * @author Hibernate Tools
 */
@Stateless
public class CustomerListHome {

	private static final Log log = LogFactory.getLog(CustomerListHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CustomerList transientInstance) {
		log.debug("persisting CustomerList instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CustomerList persistentInstance) {
		log.debug("removing CustomerList instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CustomerList merge(CustomerList detachedInstance) {
		log.debug("merging CustomerList instance");
		try {
			CustomerList result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CustomerList findById(CustomerListId id) {
		log.debug("getting CustomerList instance with id: " + id);
		try {
			CustomerList instance = entityManager.find(CustomerList.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
