// default package
// Generated 21/01/2020 23:08:10 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class FilmCategory.
 * @see .FilmCategory
 * @author Hibernate Tools
 */
@Stateless
public class FilmCategoryHome {

	private static final Log log = LogFactory.getLog(FilmCategoryHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(FilmCategory transientInstance) {
		log.debug("persisting FilmCategory instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(FilmCategory persistentInstance) {
		log.debug("removing FilmCategory instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public FilmCategory merge(FilmCategory detachedInstance) {
		log.debug("merging FilmCategory instance");
		try {
			FilmCategory result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public FilmCategory findById(FilmCategoryId id) {
		log.debug("getting FilmCategory instance with id: " + id);
		try {
			FilmCategory instance = entityManager.find(FilmCategory.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
