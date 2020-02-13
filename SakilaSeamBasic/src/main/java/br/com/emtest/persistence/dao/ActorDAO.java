package br.com.emtest.persistence.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.log.Log;

import br.com.emtest.persistence.entities.Actor;

/**
 * Home object for domain model class Actor.
 * @see .Actor
 * @author Hibernate Tools
 */
@AutoCreate
@Name("actorDAO")
@Scope(ScopeType.SESSION)
public class ActorDAO implements Serializable {

	private static final long serialVersionUID = -9192842194600087729L;

	@Logger
	private Log log;

	@In
	private EntityManager sakilaDatabase;
	
	private List<Actor> listaAtores = new ArrayList<>();

	@Create
	public void create() {
		this.listaAtores.add(sakilaDatabase.find(Actor.class, (short) 1));
		System.out.println("Tamanho lista: "+listaAtores.size());
	}

	public void persist(Actor transientInstance) {
		log.debug("persisting Actor instance");
		try {
			sakilaDatabase.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Actor persistentInstance) {
		log.debug("removing Actor instance");
		try {
			sakilaDatabase.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Actor merge(Actor detachedInstance) {
		log.debug("merging Actor instance");
		try {
			Actor result = sakilaDatabase.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Actor findById(Short id) {
		log.debug("getting Actor instance with id: " + id);
		try {
			Actor instance = sakilaDatabase.find(Actor.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
