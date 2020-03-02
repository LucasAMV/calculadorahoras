// default package
// Generated 21/01/2020 23:08:10 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class FilmList.
 * @see .FilmList
 * @author Hibernate Tools
 */
@Stateless
public class FilmListHome {

	private static final Log log = LogFactory.getLog(FilmListHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(FilmList transientInstance) {
		log.debug("persisting FilmList instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(FilmList persistentInstance) {
		log.debug("removing FilmList instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public FilmList merge(FilmList detachedInstance) {
		log.debug("merging FilmList instance");
		try {
			FilmList result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public FilmList findById(FilmListId id) {
		log.debug("getting FilmList instance with id: " + id);
		try {
			FilmList instance = entityManager.find(FilmList.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
