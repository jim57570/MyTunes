package dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DaoJPA {
	
	private static EntityManagerFactory emf = null;
	private static EntityManager em = null;
	private static EntityTransaction tx = null;
	
	public static EntityManager getManager() {
		if(em == null) {
			emf = Persistence.createEntityManagerFactory("MyTunes"); //Pour indiquer la persistenceUnit à utiliser (bdd)
			em = emf.createEntityManager();
			tx = em.getTransaction();
		}
		if(!tx.isActive()) {
			tx.begin(); //Pour vérifier qu'une transaction est active dans l'EM, sinon risque d'exceptions
		}
		
		return em;
	}

}
