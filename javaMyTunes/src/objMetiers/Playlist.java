package objMetiers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Playlist")
public class Playlist {
	
	@Id //clé primaire
	@GeneratedValue(strategy=GenerationType.IDENTITY) //auto increment
	@Column(name="Id")
	private int id = -1;
	
	@Column(name="nomPlaylist")
	private String nom = null;
	
	@ManyToMany //pour gérer la relation entre Playlist <> Chanson
	@JoinTable(name="ContientPlaylist",
	joinColumns= {
			@JoinColumn(table="Playlist", name="idPlaylist", referencedColumnName="id")
	},
	inverseJoinColumns= {
			@JoinColumn(table="chanson", name="idChanson", referencedColumnName="id")
	})
	private List<Chanson> contient = new ArrayList<Chanson>();

	public Playlist() {
		super();
	}
	
	public Playlist(String nom) {
		super();
		this.nom = nom;
	}

	public Playlist(String nom, List<Chanson> contient) {
		super();
		this.nom = nom;
		this.contient = contient;
	}

	public Playlist(int id, String nom, List<Chanson> contient) {
		super();
		this.id = id;
		this.nom = nom;
		this.contient = contient;
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

	public List<Chanson> getContient() {
		return contient;
	}

	public void setContient(List<Chanson> contient) {
		this.contient = contient;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Playlist))
			return false;
		Playlist other = (Playlist) obj;
		if (contient == null) {
			if (other.contient != null)
				return false;
		} else if (!contient.equals(other.contient))
			return false;
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
		return "Playlist [id=" + id + ", nom=" + nom + ", contient=" + contient + "]";
	}
	
	// TODO VOIR POUR AJOUTER METHODE POUR TESTER SI LE TITRE DE LA PLAYLIST EST VIDE OU NON
	
}
