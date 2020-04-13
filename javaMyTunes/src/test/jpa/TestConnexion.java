package test.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

public class TestConnexion {
	
	@Test
	public void test() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("javaMyTunes");
		emf.createEntityManager();
	}

}
