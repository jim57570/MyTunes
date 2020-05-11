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
	
	private Genre genre = new Genre();
	
	public Genre getGenre() {
		return genre;
	}
	
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public int getIdGenre() {
		return genre.getId();
	}
	
	public void setIdGenre(int id) {
		System.out.println("Genre = " + id);
		genre = DaoGenreJPA.getInstance().get(id);
	}

	public List<Genre> getGenres() {
		return DaoGenreJPA.getInstance().loadAll();
	}
}
