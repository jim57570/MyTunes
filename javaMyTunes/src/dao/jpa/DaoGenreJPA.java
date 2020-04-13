package dao.jpa;

import java.util.List;

import dao.DaoGenre;
import objMetiers.Genre;

public class DaoGenreJPA extends DaoJPA implements DaoGenre {
	
	static private DaoGenreJPA instance = null;
	
	private DaoGenreJPA() {
	}
	
	/**
	 * Design pattern singleton
	 * @return instance de DaoGenre
	 */
	static public DaoGenre getInstance() {
		if(instance == null)
			instance = new DaoGenreJPA();
		
		return instance;
	}

	@Override
	public Genre get(int id) {
		Genre g = DaoJPA.getManager().find(Genre.class, id);
		return g;
	}

	@Override
	public Genre get(String nom) {
		// requete JPQL avec un parametre
		List<Genre> listGenre = DaoJPA.getManager()
				.createQuery("SELECT g FROM Genre g WHERE Genre.nomGenre LIKE ?1", Genre.class)
				.setParameter(1, nom)
				.getResultList();
		if(listGenre.size() == 0)
			return null;
		else
			return listGenre.get(0); //Si la requête renvoie plusieurs résultats, on prend le premier (choix arbitraire)
	}

	@Override
	public void save(Genre o) {
		// TODO A supprimer (on ne doit pas ajouter ou supprimer des genres)
		
	}

	@Override
	public void remove(Genre o) {
		// TODO A supprimer (on ne doit pas ajouter ou supprimer des genres)
		
	}

	@Override
	public List<Genre> loadAll() {
		return DaoJPA.getManager().createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
	}

}
