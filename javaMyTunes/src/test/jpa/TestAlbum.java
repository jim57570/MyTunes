package test.jpa;


import org.junit.Before;
import org.junit.Test;

import dao.DaoAlbum;
import dao.jpa.DaoAlbumJPA;
import dao.jpa.DaoJPA;
import objMetiers.Album;

public class TestAlbum {
	
	private DaoAlbum dao;
	private Album a;
	
	@Before
	public void init() {
		//a = new Album("album1");
		dao = DaoAlbumJPA.getInstance();
		//DaoJPA.viderBase();
	}
	
	@Test
	public void testGetId() {
		a = dao.get(2);
	}

}
