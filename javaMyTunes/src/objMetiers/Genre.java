package objMetiers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Genre")
public class Genre {
	
	@Id //clé primaire
	@GeneratedValue(strategy=GenerationType.IDENTITY) //auto increment
	@Column(name="Id")
	private int id = -1;
	
	@Column(name="nomGenre")
	private String nom = null;
	
	@OneToMany(mappedBy="genre")
	private List<Chanson> chansons = new ArrayList<Chanson>();

	public Genre() {
		super();
	}

	public Genre(String nom) {
		super();
		this.nom = nom;
	}

	public Genre(int id, String nom) {
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
	
	public List<Chanson> getChansons() {
		return chansons;
	}

	public void setChansons(List<Chanson> chansons) {
		this.chansons = chansons;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Genre))
			return false;
		Genre other = (Genre) obj;
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
		return "Genre [id=" + id + ", nom=" + nom + "]";
	}
	
}
