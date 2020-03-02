package br.com.emtest.persistence.dao;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.emtest.persistence.entities.Payment;

/**
 * Home object for domain model class Payment.
 * @see .Payment
 * @author Hibernate Tools
 */
@AutoCreate
@Name("paymentDAO")
@Scope(ScopeType.SESSION)
public class PaymentDAO {

	private static final Log log = LogFactory.getLog(PaymentDAO.class);

	@In
	private EntityManager entityManager;

	public void persist(Payment transientInstance) {
		log.debug("persisting Payment instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Payment persistentInstance) {
		log.debug("removing Payment instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Payment merge(Payment detachedInstance) {
		log.debug("merging Payment instance");
		try {
			Payment result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Payment findById(Short id) {
		log.debug("getting Payment instance with id: " + id);
		try {
			Payment instance = entityManager.find(Payment.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
