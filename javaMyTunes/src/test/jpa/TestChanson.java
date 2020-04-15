package test.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import dao.DaoArtiste;
import dao.DaoChanson;
import dao.jpa.DaoArtisteJPA;
import dao.jpa.DaoChansonJPA;
import dao.jpa.DaoJPA;
import objMetiers.Artiste;
import objMetiers.Chanson;

public class TestChanson {
	
	private DaoChanson dao;
	
	@Before
	public void init() {
		dao = DaoChansonJPA.getInstance();
		//DaoJPA.viderBase();
	}
	
	@Test
	public void testChanson() {
		//test enregistrement chanson seule
		Chanson c = new Chanson("chanson 1", "chanson1.mp3");
		
		dao.save(c);
		
		assertEquals(c, dao.get(c.getId()));
	}
	
	@Test
	public void testChansonAvecTags() {
		//test d'enregistrement d'une chanson avec un tag (ici artiste)
		DaoArtiste daoArtiste = DaoArtisteJPA.getInstance();
		Artiste a = daoArtiste.get(1);
		
		Chanson c = new Chanson("chanson 2", "chanson2.mp3");
		c.setArtiste(a);
		
		dao.save(c);
		
		assertEquals(c, dao.get(c.getId()));
	}
	
	@Test
	public void testRemoveAvecTags() {
		// test suppression d'une chanson avec un tag
		Chanson c = dao.get(2);
		dao.remove(c);
		
		assertEquals(-1, c.getId());
	}
	
	@Test
	public void testListAll() {
		//on test si la fonction retourne bien une liste
		assertNotNull(dao.loadAll());
	}

	// TODO tester également les exceptions pour save et remove
}
