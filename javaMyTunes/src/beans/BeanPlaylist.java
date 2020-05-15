package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.component.UIComponent;

import dao.jpa.DaoChansonJPA;
import dao.jpa.DaoPlaylistJPA;
import objMetiers.Chanson;
import objMetiers.Playlist;

public class BeanPlaylist implements Serializable {
	
	private static final long serialVersionUID = 410717373831919568L;
	
	private Playlist playlist = new Playlist();
	
	private UIComponent commandLink;
	
	private UIComponent chansonLink;
	
	public Playlist getPlaylist() {
		return playlist;
	}
	
	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}
	
	public int getIdPlaylist() {
		return playlist.getId();
	}
	
	public void setIdPlaylist(int id) {
		System.out.println("Playlist = " + id);
		playlist = DaoPlaylistJPA.getInstance().get(id);
	}
	
	public UIComponent getCommandLink() {
		return commandLink;
	}
	
	public void setCommandLink(UIComponent commandLink) {
		this.commandLink = commandLink;
	}
	
	public UIComponent getChansonLink() {
		return chansonLink;
	}
	
	public void setChansonLink(UIComponent chansonLink) {
		this.chansonLink = chansonLink;
	}
	
	public String save() {
		DaoPlaylistJPA.getInstance().save(playlist);
		return "PlaylistEnregistre";
	}
	
	public String edit() {
		System.out.println(playlist);
		DaoPlaylistJPA.commit();
		return "PlaylistModifie";
	}
	
	public String delete() {
		//permet de récupérer la playlist passé en paramètre pour la suppression
		Playlist playlist = (Playlist)commandLink.getAttributes().get("playlist");
		DaoPlaylistJPA.getInstance().remove(playlist);
		return "PlaylistSupprime";
	}
	
	public String deleteChanson() {
		//permet de récupérer la chanson passé en paramètre pour la suppression
		Chanson chanson = (Chanson)chansonLink.getAttributes().get("chanson");
		//Chanson chanson = DaoChansonJPA.getInstance().get(6);
		System.out.println(chanson);
		this.playlist.getContient().remove(chanson);
		return "ChansonPlaylistSupprime";
	}
	
	public String addChanson() {
		Chanson chanson = (Chanson)chansonLink.getAttributes().get("chansonAdd");
		System.out.println(chanson);
		this.playlist.getContient().add(chanson);
		return "ChansonPlaylistAjoute";
	}
	
	public List<Playlist> getPlaylists() {
		return DaoPlaylistJPA.getInstance().loadAll();
	}
}
