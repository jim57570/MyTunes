package beans;

import java.io.Serializable;
import java.util.List;

import dao.jpa.DaoPlaylistJPA;
import objMetiers.Playlist;

public class BeanPlaylist implements Serializable {
	
	private static final long serialVersionUID = 410717373831919568L;
	
	private Playlist playlist = new Playlist();
	
	public Playlist getPlaylist() {
		return playlist;
	}
	
	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}
	
	public List<Playlist> getPlaylists() {
		return DaoPlaylistJPA.getInstance().loadAll();
	}
	
	public int getIdPlaylist() {
		return playlist.getId();
	}
	
	public void setIdPlaylist(int id) {
		System.out.println("Playlist = " + id);
		playlist = DaoPlaylistJPA.getInstance().get(id);
	}

}
