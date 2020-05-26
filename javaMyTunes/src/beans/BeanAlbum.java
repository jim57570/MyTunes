package beans;

import java.io.Serializable;
import java.util.List;

import dao.jpa.DaoAlbumJPA;
import dao.jpa.DaoChansonJPA;
import objMetiers.Album;
import objMetiers.Chanson;
import tagEdit.TagEdit;

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
	
	public String edit() {
		//on remet à jour la bdd
		//TODO mettre à jour également les tags ayant comme nom cette album !!
		System.out.println(album);
		
		List<Chanson> listChanson = DaoChansonJPA.getInstance().loadAll();
		for(int i=0; i<listChanson.size(); i++) {
			TagEdit.exportTag(listChanson.get(i), listChanson.get(i).getNomFichier());
		}
		
		
		DaoAlbumJPA.commit();
		return "AlbumModifie";
	}

}
