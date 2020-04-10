package dao.jpa;

import java.util.List;

import dao.DaoArtiste;
import objMetiers.Artiste;

public class DaoArtisteJPA extends DaoJPA implements DaoArtiste {
	
	static private DaoArtiste instance = null;
	
	private DaoArtisteJPA() {
	}
	
	/**
	 * Design pattern singletion
	 * @return instance de DaoArtiste
	 */
	static public DaoArtiste getInstance() {
		if(instance == null)
			instance = new DaoArtisteJPA();
		
		return instance;
	}

	@Override
	public Artiste get(int id) {
		Artiste a = DaoJPA.getManager().find(Artiste.class, id);
		return a;
	}

	@Override
	public Artiste get(String nom) {
		//requête JPQL avec un paramètre
		List<Artiste> listArtiste = DaoJPA.getManager()
				.createQuery("SELECT a FROM Artiste a WHERE a.nomArtiste LIKE ?1", Artiste.class)
				.setParameter(1, nom)
				.getResultList();
		if(listArtiste.size() == 0)
			return null;
		else
			return listArtiste.get(0); //Si la requête renvoie plusieurs résultats, on prend le premier (choix arbitraire)
	}

	@Override
	public void save(Artiste o) {
		// TODO Ajouter méthodes pour vérifier un artiste avant de l'enregistrer
		DaoJPA.getManager().persist(o);
		DaoJPA.commit(); //discutable de commiter ici
		
	}

	@Override
	public void remove(Artiste o) {
		// TODO Ajouter méthodes pour vérifier un artiste avant de le supprimer
		Artiste artisteToRemove = DaoJPA.getManager().merge(o);
		DaoJPA.getManager().remove(artisteToRemove);
		DaoJPA.commit();
		
		o.setId(-1);
	}

	@Override
	public List<Artiste> loadAll() {
		return DaoJPA.getManager().createQuery("SELECT a FROM Artiste a", Artiste.class).getResultList();
	}

}
