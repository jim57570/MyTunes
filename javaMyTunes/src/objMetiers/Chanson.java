package objMetiers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="chanson")
public class Chanson {
	
	@Id //clé primaire
	@GeneratedValue(strategy=GenerationType.IDENTITY) //auto increment
	@Column(name="Id")
	private int id = -1;
	
	@Column(name="titre")
	private String titre = null;
	
	@Column(name="nomFichier")
	private String nomFichier = null;
	
	@OneToOne //cardinalité 0,1 dans notre schéma E/A
	@JoinColumn(name="idAlbum") //clé étrangère sur Album_id
	private Album album = null;
	
	@OneToOne //cardinalité 0,1 dans notre schéma E/A
	@JoinColumn(name="idArtiste") //clé étrangère sur Artiste_id
	private Artiste artiste = null;
	
	@OneToOne //cardinalité 0,1 dans notre schéma E/A
	@JoinColumn(name="idGenre") //clé étrangère sur Genre_id
	private Genre genre = null;
	
	@ManyToMany(mappedBy="contient") //pour gérer la relation entre playlist <> chanson
	private List<Playlist> listePlaylists = new ArrayList<Playlist>();

	public Chanson() {
		super();
	}

	public Chanson(String titre, String nomFichier) {
		super();
		this.titre = titre;
		this.nomFichier = nomFichier;
	}
	
	public Chanson(int id, String titre, String nomFichier) {
		super();
		this.id = id;
		this.titre = titre;
		this.nomFichier = nomFichier;
	}

	public Chanson(int id, String titre, String nomFichier, Album album, Artiste artiste, Genre genre,
			List<Playlist> listePlaylists) {
		super();
		this.id = id;
		this.titre = titre;
		this.nomFichier = nomFichier;
		this.album = album;
		this.artiste = artiste;
		this.genre = genre;
		this.listePlaylists = listePlaylists;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getNomFichier() {
		return nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Artiste getArtiste() {
		return artiste;
	}

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<Playlist> getListePlaylists() {
		return listePlaylists;
	}

	public void setListePlaylists(List<Playlist> listePlaylists) {
		this.listePlaylists = listePlaylists;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Chanson))
			return false;
		Chanson other = (Chanson) obj;
		if (album == null) {
			if (other.album != null)
				return false;
		} else if (!album.equals(other.album))
			return false;
		if (artiste == null) {
			if (other.artiste != null)
				return false;
		} else if (!artiste.equals(other.artiste))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id != other.id)
			return false;
		if (nomFichier == null) {
			if (other.nomFichier != null)
				return false;
		} else if (!nomFichier.equals(other.nomFichier))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Chanson [id=" + id + ", titre=" + titre + ", nomFichier=" + nomFichier + ", album=" + album
				+ ", artiste=" + artiste + ", genre=" + genre + "]";
	}
	
	// TODO VOIR POUR AJOUTER METHODE POUR TESTER SI LE TITRE DE LA CHANSON EST VIDE OU NON
	// TODO Méthodes pour pouvoir modifier les tags id3
}
