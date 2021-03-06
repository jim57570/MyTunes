package dao.jpa;

import java.util.List;

import dao.DaoChanson;
import objMetiers.Chanson;

public class DaoChansonJPA extends DaoJPA implements DaoChanson {
	
	static private DaoChanson instance = null;
	
	private DaoChansonJPA() {
	}
	
	/**
	 * Design pattern singleton
	 * @return instance de DaoChanson
	 */
	static public DaoChanson getInstance() {
		if(instance == null)
			instance = new DaoChansonJPA();
		
		return instance;
	}
	
	@Override
	public Chanson get(int id) {
		Chanson c = DaoJPA.getManager().find(Chanson.class, id);
		return c;
	}

	@Override
	public Chanson get(String nom) {
		//requête JPQL avec un paramètre
		List<Chanson> listChanson = DaoJPA.getManager()
				.createQuery("SELECT c FROM Chanson c WHERE c.titre LIKE ?1", Chanson.class) //c.titre par rapport à l'objet métier et pas à la bdd !
				.setParameter(1, nom)
				.getResultList();
		if(listChanson.size() == 0)
			return null;
		else
			return listChanson.get(0); //Si la requête renvoie plusierus résultats, on prend le premier (choix arbitraire)
	}
	
	//TODO A voir pour généraliser pour les autres objets
	@Override
	public List<Chanson> find(String nom) {
		//requête JPQL avec un paramètre
		List<Chanson> listChanson = DaoJPA.getManager()
				.createQuery("SELECT c FROM Chanson c WHERE c.titre LIKE ?1 OR c.nomFichier LIKE ?1", Chanson.class) //c.titre par rapport à l'objet métier et pas à la bdd !
				.setParameter(1, nom)
				.getResultList();
		return listChanson;
	}

	@Override
	public void save(Chanson o) {
		// TODO Ajouter méthodes pour vérifier une chanson avant de l'enregistrer
		DaoJPA.getManager().persist(o);
		DaoJPA.commit(); //Discutable de commiter ici
	}

	@Override
	public void remove(Chanson o) {
		// TODO Ajouter méthodes pour vérifier une chanson avant de la supprimer
		Chanson chansonToRemove = DaoJPA.getManager().merge(o);
		DaoJPA.getManager().remove(chansonToRemove);
		DaoJPA.commit();
		
		o.setId(-1);
	}

	@Override
	public List<Chanson> loadAll() {
		return DaoJPA.getManager().createQuery("SELECT c FROM Chanson c", Chanson.class).getResultList();
	}

}
