// default package
// Generated 21/01/2020 23:08:10 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class SalesByStore.
 * @see .SalesByStore
 * @author Hibernate Tools
 */
@Stateless
public class SalesByStoreHome {

	private static final Log log = LogFactory.getLog(SalesByStoreHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(SalesByStore transientInstance) {
		log.debug("persisting SalesByStore instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(SalesByStore persistentInstance) {
		log.debug("removing SalesByStore instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public SalesByStore merge(SalesByStore detachedInstance) {
		log.debug("merging SalesByStore instance");
		try {
			SalesByStore result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SalesByStore findById(SalesByStoreId id) {
		log.debug("getting SalesByStore instance with id: " + id);
		try {
			SalesByStore instance = entityManager.find(SalesByStore.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
