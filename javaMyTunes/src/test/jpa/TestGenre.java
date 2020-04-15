package test.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import dao.DaoGenre;
import dao.jpa.DaoGenreJPA;
import dao.jpa.DaoJPA;

public class TestGenre {
	
	private DaoGenre dao;
	
	@Before
	public void init() {
		dao = DaoGenreJPA.getInstance();
	}
	
	@Test
	public void testGet() {
		//test pour récupérer des genres qui existent ou non
		assertNotNull(dao.get(1));
		assertNotNull(dao.get("Blues"));
		
		assertNull(dao.get(0));
		assertNull(dao.get("genre"));
	}
	
	@Test
	public void testLoadAll() {
		//test pour savoir si on récupère bien une liste (ici de 147 genres normalement)
		assertEquals(147, dao.loadAll().size());
	}

}
