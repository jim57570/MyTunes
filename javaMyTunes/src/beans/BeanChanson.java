package beans;

import java.io.Serializable;
import java.util.List;

import dao.jpa.DaoChansonJPA;
import objMetiers.Chanson;
import tagEdit.TagEdit;

public class BeanChanson implements Serializable {
	
	private Chanson chanson = new Chanson();
	
	public Chanson getChanson() {
		return chanson;
	}
	
	public void setChanson(Chanson chanson) {
		this.chanson = chanson;
	}
	
	public String save() {
		//DaoChansonJPA.getInstance().save(chanson);
		//test importChanson avec tags
		DaoChansonJPA.getInstance().save(TagEdit.importChanson(chanson.getNomFichier()));
		return "ChansonEnregistre";
	}
	
	public List<Chanson> getChansons() {
		return DaoChansonJPA.getInstance().loadAll();
	}

}
