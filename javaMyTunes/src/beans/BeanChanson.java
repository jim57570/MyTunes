package beans;

import java.io.Serializable;
import java.util.List;

import dao.jpa.DaoChansonJPA;
import objMetiers.Chanson;

public class BeanChanson implements Serializable {
	
	private Chanson chanson = new Chanson();
	
	public Chanson getChanson() {
		return chanson;
	}
	
	public void setChanson(Chanson chanson) {
		this.chanson = chanson;
	}
	
	public String save() {
		DaoChansonJPA.getInstance().save(chanson);
		return "ChansonEnregistre";
	}
	
	public List<Chanson> getChansons() {
		return DaoChansonJPA.getInstance().loadAll();
	}

}
