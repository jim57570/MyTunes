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
	
	private Artiste artiste = new Artiste();
	
	public Artiste getArtiste() {
		return artiste;
	}
	
	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}
	
	public int getIdArtiste() {
		return artiste.getId();
	}
	
	public void setIdArtiste(int id) {
		System.out.println("Artiste = " + id);
		artiste = DaoArtisteJPA.getInstance().get(id);
	}
	
	public List<Artiste> getArtistes() {
		return DaoArtisteJPA.getInstance().loadAll();
	}

}
