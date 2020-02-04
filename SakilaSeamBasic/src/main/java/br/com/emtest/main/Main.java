package br.com.emtest.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.emtest.persistence.entities.Actor;

public class Main {
	
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("empu");
		EntityManager em = entityManagerFactory.createEntityManager();
		System.out.println(em);
		
		Actor a1 = em.find(Actor.class, (short) 1);
		
		System.out.println(a1.getFirstName());
		
		em.close();
	}
	
}
