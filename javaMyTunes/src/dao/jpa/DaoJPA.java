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
			emf = Persistence.createEntityManagerFactory("javaMyTunes"); //Pour indiquer la persistenceUnit à utiliser (bdd)
			em = emf.createEntityManager();
			tx = em.getTransaction();
		}
		if(!tx.isActive()) {
			tx.begin(); //Pour vérifier qu'une transaction est active dans l'EM, sinon risque d'exceptions
		}
		
		return em;
	}
	
	/**
	 * Pour commiter une transaction
	 * Mais avant on s'assure que les objets sont bien synchronisés sinon on peut rencontrer quelques problèmes
	 */
	public static void commit() {
		em.flush();
		em.clear();
		tx.commit();
	}
	
	/**
	 * Pour annuler une transaction
	 */
	public static void rollback() {
		tx.rollback();
	}
	
	/**
	 * Pour terminer une session
	 */
	public static void close() {
		em.close(); em = null;
		emf.close(); emf = null;
	}
	
	/**
	 * Pour pouvoir remettre la base à O avant des tests
	 * Sauf la table Genre qui contient la liste des genres possible
	 */
	public static void viderBase() {
		//Album
		getManager().createQuery("DELETE FROM Album").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE Album AUTO_INCREMENT = 1").executeUpdate();
		
		//Artiste
		getManager().createQuery("DELETE FROM Artiste").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE Artiste AUTO_INCREMENT = 1").executeUpdate();
		
		//ContientPlaylist
		getManager().createQuery("DELETE FROM ContientPlaylist").executeUpdate();
		
		//Chanson
		getManager().createQuery("DELETE FROM Chanson").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE Chanson AUTO_INCREMENT = 1").executeUpdate();
		
		//Playlist
		getManager().createQuery("DELETE FROM Playlist").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE Playlist AUTO_INCREMENT = 1").executeUpdate();
	}
}
