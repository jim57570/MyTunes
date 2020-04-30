package beans;

import java.io.Serializable;
import java.util.List;

import dao.jpa.DaoGenreJPA;
import objMetiers.Genre;

public class BeanGenre implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Genre> getGenres() {
		return DaoGenreJPA.getInstance().loadAll();
	}
}
