package test.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import dao.DaoArtiste;
import dao.jpa.DaoArtisteJPA;
import objMetiers.Artiste;

public class TestArtiste {
	
	private DaoArtiste dao;
	private Artiste a, a2;
	
	@Before
	public void init() {
		//artiste déjà dans la bdd
		a = new Artiste(1, "artiste test");
		a2 = new Artiste("artiste test 2");
		dao = DaoArtisteJPA.getInstance();
		//DaoJPA.viderBase();
	}
	
	@Test
	public void testGetId() {
		//test pour voir si on récupère bien le même objet dans la bdd
		assertEquals(a, dao.get(a.getId()));
				
		//test si on recupère un objet inexistant dans la bdd
		assertNull(dao.get(0));
	}
	
	@Test
	public void testGetNom() {
		//test pour voir si on récupère bien le même objet dans la bdd
		assertEquals(a, dao.get("artiste test"));
				
		//test si on recupère un objet inexistant dans la bdd
		assertNotEquals(a, dao.get("artiste"));
	}
	
	@Test
	public void testSave() {
		// test de l'enregistrement et si l'id se met à jour
		dao.save(a2);
		assertEquals(a2, dao.get(a2.getId()));
	}
	
	@Test
	public void testRemove() {
		//apparement la suppression fonctionne si l'objet a été enregistré ds la même fonction
		// TODO voir avec le prof si c'est normal
		dao.save(a2);
		
		//test de la suppression et du changement de l'id de l'album
		dao.remove(a2);
		assertEquals(-1, a2.getId());
	}
	
	public void testListAll() {
		//on test si la fonction retourne bien une liste
		assertNotNull(dao.loadAll());
	}
	
	// TODO tester également les exceptions pour save et remove
}
