package beans;

import java.io.Serializable;
import java.util.List;

import dao.jpa.DaoChansonJPA;
import objMetiers.Chanson;
import tagEdit.TagEdit;

public class BeanChanson implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Chanson chanson = new Chanson();
	
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
	}
	
	public String save() {
		//DaoChansonJPA.getInstance().save(chanson);
		//test importChanson avec tags
		//TODO déplacer la musique dans le répertoire des musiques sur le serveur
		DaoChansonJPA.getInstance().save(TagEdit.importChanson(chanson.getNomFichier()));
		return "ChansonEnregistre";
	}
	
	public String edit() {
		//on remet à jour les tags et on met à jour la bdd
		System.out.println(chanson);
		TagEdit.exportTag(chanson);
		DaoChansonJPA.commit();
		return "ChansonModifie";
	}
	
	public List<Chanson> getChansons() {
		return DaoChansonJPA.getInstance().loadAll();
	}

}