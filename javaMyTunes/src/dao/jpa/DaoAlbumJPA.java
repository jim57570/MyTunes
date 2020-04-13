package dao.jpa;

import java.util.List;

import dao.DaoAlbum;
import objMetiers.Album;

public class DaoAlbumJPA extends DaoJPA implements DaoAlbum {
	
	static private DaoAlbumJPA instance = null;
	
	private DaoAlbumJPA() {
	}
	
	/**
	 * Design pattern singleton
	 * @return instance de DaoAlbum
	 */
	static public DaoAlbum getInstance() {
		if(instance == null)
			instance = new DaoAlbumJPA();
		
		return instance;
	}

	@Override
	public Album get(int id) {
		Album a = DaoJPA.getManager().find(Album.class, id);
		return a;
	}

	@Override
	public Album get(String nom) {
		//requête JPQL avec un paramètre
		List<Album> listAlbum = DaoJPA.getManager()
				.createQuery("SELECT a FROM Album a WHERE a.nomAlbum LIKE ?1", Album.class)
				.setParameter(1, "%"+nom+"%")
				.getResultList();
		if(listAlbum.size() == 0)
			return null;
		else
			return listAlbum.get(0); //Si la requête renvoie plusieurs résultats, on prend le premier (choix arbitraire)			
	}

	@Override
	public void save(Album o) {
		// TODO Ajouter méthodes pour vérifier un album avant de l'enregistrer
		DaoJPA.getManager().persist(o);
		DaoJPA.commit(); //discutable de commiter ici	
	}

	@Override
	public void remove(Album o) {
		// TODO Ajouter méthodes pour vérifier un album avant de le supprimer
		Album albumToRemove = DaoJPA.getManager().merge(o);
		DaoJPA.getManager().remove(albumToRemove);
		DaoJPA.commit();
		
		o.setId(-1);
	}

	@Override
	public List<Album> loadAll() {
		return DaoJPA.getManager().createQuery("SELECT a FROM Album a", Album.class).getResultList();
	}

}
