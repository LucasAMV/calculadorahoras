package br.com.emtest.main;

public class Main {
	
	public static void main(String[] args) {
		/*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("empu");
		EntityManager em = entityManagerFactory.createEntityManager();
		System.out.println(em);
		
		Actor a1 = em.find(Actor.class, (short) 1);
		
		System.out.println(a1.getFirstName());
		
		em.close();
		*/
		
		System.out.println(Package.getPackage("br.com.emtest.persistence.dao"));
	}
}
