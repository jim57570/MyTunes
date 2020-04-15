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
				.createQuery("SELECT g FROM Genre g WHERE g.nom LIKE ?1", Genre.class) //g.nom par rapport à l'objet métier et pas à la bdd !
				.setParameter(1, nom)
				.getResultList();
		if(listGenre.size() == 0)
			return null;
		else
			return listGenre.get(0); //Si la requête renvoie plusieurs résultats, on prend le premier (choix arbitraire)
	}

	@Override
	public List<Genre> loadAll() {
		return DaoJPA.getManager().createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
	}

}
