package test.jpa;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import dao.DaoChanson;
import dao.DaoPlaylist;
import dao.jpa.DaoChansonJPA;
import dao.jpa.DaoJPA;
import dao.jpa.DaoPlaylistJPA;
import objMetiers.Chanson;
import objMetiers.Playlist;

public class TestPlaylist {
	
	private DaoPlaylist dao;
	
	@Before
	public void init() {
		dao = DaoPlaylistJPA.getInstance();
		//DaoJPA.viderBase();
	}
	
	@Test
	public void testPlaylist() {
		//test enregistrement playlist seule
		Playlist p = new Playlist("playlist2");
		
		dao.save(p);
		assertEquals(p, dao.get(p.getId()));
	}
	
	@Test
	public void testPlaylistAvecChanson() {
		//test ajout chanson à une playlist
		DaoChanson daoChanson = DaoChansonJPA.getInstance();
		
		Chanson c = daoChanson.get(3);
		assertEquals(0, c.getListePlaylists().size());
		
		Playlist p = dao.get(1);
		p.getContient().add(c);
		
		//daoChanson.save(c);
		dao.save(p);
		
		assertEquals(2, p.getContient().size());
		assertEquals(1, c.getListePlaylists().size());
	}
	
	@Test
	public void testRemove() {
		//test pour enlever une playlist
		Playlist p = dao.get(2);
		dao.remove(p);
		
		assertEquals(-1, p.getId());
	}
	
	@Test
	public void testRemoveContient() {
		//test pour enlever une musique d'une playlist
		Playlist p = dao.get(3);
		
		p.getContient().remove(0);
		
		dao.save(p);
		assertEquals(0, p.getContient().size());
	}
	
	// TODO tester également les exceptions pour save et remove
}
