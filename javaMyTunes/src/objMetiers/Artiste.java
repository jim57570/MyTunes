package objMetiers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Artiste")
public class Artiste {
	
	@Id //clé primaire
	@GeneratedValue(strategy=GenerationType.IDENTITY) //auto increment
	@Column(name="Id")
	private int id = -1;
	
	@Column(name="nomArtiste")
	private String nom = null;

	public Artiste() {
		super();
	}

	public Artiste(String nom) {
		super();
		this.nom = nom;
	}

	public Artiste(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Artiste))
			return false;
		Artiste other = (Artiste) obj;
		if (id != other.id)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Artiste [id=" + id + ", nom=" + nom + "]";
	}
	
	// TODO VOIR POUR AJOUTER METHODE POUR TESTER SI LE NOM DE L'ARTISTE EST VIDE OU NON
	
}
