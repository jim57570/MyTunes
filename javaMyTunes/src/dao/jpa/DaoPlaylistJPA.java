package dao.jpa;

import java.util.List;

import dao.DaoPlaylist;
import objMetiers.Playlist;

public class DaoPlaylistJPA extends DaoJPA implements DaoPlaylist {
	
	static private DaoPlaylistJPA instance = null;
	
	private DaoPlaylistJPA() {
	}
	
	/**
	 * Design pattern singleton
	 * @return instance de DaoPlaylist
	 */
	
	public DaoPlaylist getInstance() {
		if(instance == null)
			instance = new DaoPlaylistJPA();
		
		return instance; 
	}

	@Override
	public Playlist get(int id) {
		Playlist p = DaoJPA.getManager().find(Playlist.class, id);
		return p;
	}

	@Override
	public Playlist get(String nom) {
		//requête JPQL avec un paramètre
		List<Playlist> listPlaylist = DaoJPA.getManager()
				.createQuery("SELECT p FROM Playlist p WHERE Playlist.nomPlaylist LIKE ?1", Playlist.class)
				.setParameter(1, nom)
				.getResultList();
		if(listPlaylist.size() == 0)
			return null;
		else
			return listPlaylist.get(0); //si la requête renvoie plusieurs résultats, on prend le premier (choix arbitraire)
	}

	@Override
	public void save(Playlist o) {
		// TODO Ajouter méthodes pour vérifier une playlist avant de l'enregistrer
		DaoJPA.getManager().persist(o);
		DaoJPA.commit(); //discutable de commiter ici
	}

	@Override
	public void remove(Playlist o) {
		// TODO Ajouter méthodes pour vérifier une playlist avant de la supprimer
		Playlist playlistToRemove = DaoJPA.getManager().merge(o);
		DaoJPA.getManager().remove(playlistToRemove);
		DaoJPA.commit();
		
		o.setId(-1);
		// TODO voir pour supprimer les autres attributs de playlist
	}

	@Override
	public List<Playlist> loadAll() {
		return DaoJPA.getManager().createQuery("SELECT p FROM Playlist p", Playlist.class).getResultList();
	}

}
