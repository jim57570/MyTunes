package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;

import dao.jpa.DaoChansonJPA;
import objMetiers.Chanson;
import tagEdit.TagEdit;

public class BeanChanson implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Chanson chanson = new Chanson();
	
	private UIComponent commandLink;
	
	private String recherche = "";
	
	private String songName = "";
	
	public String getRecherche() {
		return recherche;
	}
	
	public void setRecherche(String recherche) {
		this.recherche = recherche;
	}
	
	public Chanson getChanson() {
		return chanson;
	}
	
	public void setChanson(Chanson chanson) {
		this.chanson = chanson;
	}
	
	public int getIdChanson() {
		return chanson.getId();
	}
	
	public void setIdChanson(int id) {
		System.out.println("Chanson = " + id);
		chanson = DaoChansonJPA.getInstance().get(id);
		songName = chanson.getNomFichier();
	}
	
	public String save() {
		//DaoChansonJPA.getInstance().save(chanson);
		//test importChanson avec tags
		DaoChansonJPA.getInstance().save(TagEdit.importChanson(chanson.getNomFichier()));
		chanson = new Chanson();
		return "ChansonEnregistre";
	}
	
	public String edit() {
		//on remet à jour les tags et on met à jour la bdd
		System.out.println(chanson);
		TagEdit.exportTag(chanson, songName);
		DaoChansonJPA.commit();
		return "ChansonModifie";
	}
	
	public String delete() {
		//permet de récupérer l'objet chanson passé en paramètre pour la suppression
		Chanson chanson = (Chanson)commandLink.getAttributes().get("chanson");
		DaoChansonJPA.getInstance().remove(chanson);
		TagEdit.removeChanson(chanson);
		return "ChansonSupprime";
	}
	
	public List<Chanson> getChansons() {
		return DaoChansonJPA.getInstance().loadAll();
	}
	
	public List<Chanson> getFind() {
		return DaoChansonJPA.getInstance().find(recherche);
	}
	
	public UIComponent getCommandLink() {
		return commandLink;
	}
	
	public void setCommandLink(UIComponent commandLink) {
		this.commandLink = commandLink;
	}

}
