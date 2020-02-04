// default package
// Generated 21/01/2020 23:08:10 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class SalesByFilmCategory.
 * @see .SalesByFilmCategory
 * @author Hibernate Tools
 */
@Stateless
public class SalesByFilmCategoryHome {

	private static final Log log = LogFactory.getLog(SalesByFilmCategoryHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(SalesByFilmCategory transientInstance) {
		log.debug("persisting SalesByFilmCategory instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(SalesByFilmCategory persistentInstance) {
		log.debug("removing SalesByFilmCategory instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public SalesByFilmCategory merge(SalesByFilmCategory detachedInstance) {
		log.debug("merging SalesByFilmCategory instance");
		try {
			SalesByFilmCategory result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SalesByFilmCategory findById(SalesByFilmCategoryId id) {
		log.debug("getting SalesByFilmCategory instance with id: " + id);
		try {
			SalesByFilmCategory instance = entityManager.find(SalesByFilmCategory.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
