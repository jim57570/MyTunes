package beans;

import java.io.Serializable;
import java.util.List;

import dao.jpa.DaoArtisteJPA;
import objMetiers.Artiste;

public class BeanArtiste implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<Artiste> getArtistes() {
		return DaoArtisteJPA.getInstance().loadAll();
	}

}
