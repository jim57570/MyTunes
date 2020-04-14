package test.jpa;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import dao.DaoAlbum;
import dao.jpa.DaoAlbumJPA;
import dao.jpa.DaoJPA;
import objMetiers.Album;

public class TestAlbum {
	
	private DaoAlbum dao;
	private Album a, a2;
	
	@Before
	public void init() {
		//album déjà présent dans la bdd
		a = new Album(1 ,"test JPA");
		a2 = new Album("oui");
		dao = DaoAlbumJPA.getInstance();
		//DaoJPA.viderBase();
	}
	
	@Test
	public void testGetId() {
		//test pour voir si on récupère bien le même objet dans la bdd
		assertEquals(a, dao.get(1));
		
		//test si on recupère un objet inexistant dans la bdd
		assertEquals(null, dao.get(50));
	}
	
	@Test
	public void testGetNom() {
		//test pour voir si on récupère bien le même objet dans la bdd
		assertEquals(a, dao.get("test JPA"));
				
		//test si on recupère un objet inexistant dans la bdd
		assertNotEquals(a, dao.get("album"));	
	}
	
	@Test
	public void testSave() {
		//test de l'enregistrement et si l'id se met à jour
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
