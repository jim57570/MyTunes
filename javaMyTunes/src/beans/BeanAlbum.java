package beans;

import java.io.Serializable;
import java.util.List;

import dao.jpa.DaoAlbumJPA;
import objMetiers.Album;

public class BeanAlbum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Album album = new Album();
	
	public Album getAlbum() {
		return album;
	}
	
	public void setAlbum(Album album) {
		this.album = album;
	}
	
	public int getIdAlbum() {
		return album.getId();
	}
	
	public void setIdAlbum(int id) {
		System.out.println("Album = " + id);
		album = DaoAlbumJPA.getInstance().get(id);
	}

	public List<Album> getAlbums() {
		return DaoAlbumJPA.getInstance().loadAll();
	}

}
