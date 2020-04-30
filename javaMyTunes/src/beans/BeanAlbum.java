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

	public List<Album> getAlbums() {
		return DaoAlbumJPA.getInstance().loadAll();
	}

}
