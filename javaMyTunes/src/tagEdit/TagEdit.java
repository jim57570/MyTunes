package tagEdit;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

import dao.DaoAlbum;
import dao.DaoArtiste;
import dao.DaoGenre;
import dao.jpa.DaoAlbumJPA;
import dao.jpa.DaoArtisteJPA;
import dao.jpa.DaoGenreJPA;
import objMetiers.Album;
import objMetiers.Artiste;
import objMetiers.Chanson;
import objMetiers.Genre;

/**
 * Classe permettant la modification des tag depuis le fichier .mp3
 * @author jim
 *
 */
public class TagEdit {
	
	public static Chanson importChanson(String path) {
		// TODO déplacer le mp3 dans le dossier choisi
		Chanson c = new Chanson();
		
		DaoAlbum daoAlbum = DaoAlbumJPA.getInstance();
		DaoArtiste daoArtiste = DaoArtisteJPA.getInstance();
		DaoGenre daoGenre = DaoGenreJPA.getInstance();
		
		
		
		File mp3 = new File(path);
		if(mp3.exists()) {
			c.setNomFichier(path);
			try {
				//on recupère les tags du fichier .mp3
				AudioFile af = AudioFileIO.read(mp3);
				Tag tag = af.getTag();
				
				//si un tag est présent, on l'ajoute dans l'objet chanson
				//TITRE
				if(tag.getFirst(FieldKey.TITLE).length() > 0)
					c.setTitre(tag.getFirst(FieldKey.TITLE));
				
				//NOM DE L'ALBUM
				if(tag.getFirst(FieldKey.ALBUM).length() > 0) {
					//on vérifie dans la bdd si l'album existe, sinon on le crée
					Album album = daoAlbum.get(tag.getFirst(FieldKey.ALBUM));
					if(album == null) {
						album = new Album(tag.getFirst(FieldKey.ALBUM));
						daoAlbum.save(album);
					}
					c.setAlbum(album);
				}
				
				//NOM DE L'ARTISTE
				if(tag.getFirst(FieldKey.ARTIST).length() > 0) {
					//on vérifie dans la bdd si l'artiste existe, sinon on le crée
					Artiste artiste = daoArtiste.get(tag.getFirst(FieldKey.ARTIST));
					if(artiste == null) {
						artiste = new Artiste(tag.getFirst(FieldKey.ARTIST));
						daoArtiste.save(artiste);
					}
					c.setArtiste(artiste);
				}
				
				//GENRE
				if(tag.getFirst(FieldKey.GENRE).length() > 0) {
					//on recupère le genre correspondant depuis la bdd
					int idGenre = Integer.valueOf(tag.getFirst(FieldKey.GENRE));
					Genre genre  = daoGenre.get(idGenre);
					if(genre != null) {
						c.setGenre(genre);
					}
					else {
						System.out.println("Genre introuvable !!");
					}
				}
				
			} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
					| InvalidAudioFrameException e) {
				System.out.println("Erreur lors de l'ouverture du .mp3 par JAudioTagger !");
				e.printStackTrace();
			}
			return c;
		}
		else {
			System.out.println("Fichier inexistant !");
			return null;
		}	
	}
	
	public static void exportTag(Chanson c) {
		File mp3 = new File(c.getNomFichier());
		if(mp3.exists()) {
			try {
				AudioFile af = AudioFileIO.read(mp3);
				Tag tag = af.getTag();
				//maj des tags
				if(c.getTitre() != null)
					tag.setField(FieldKey.TITLE, c.getTitre());
				if(c.getArtiste() != null)
					tag.setField(FieldKey.ARTIST, c.getArtiste().getNom());
				if(c.getAlbum() != null)
					tag.setField(FieldKey.ALBUM, c.getAlbum().getNom());
				if(c.getGenre() != null)
					tag.setField(FieldKey.GENRE, c.getGenre().getNom());
				
				af.setTag(tag);
				af.commit();
			} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
					| InvalidAudioFrameException e) {
				System.out.println("Erreur lors de l'ouverture du .mp3 par JAudioTagger !");
				e.printStackTrace();
			} catch (CannotWriteException e) {
				System.out.println("Erreur lors de l'écriture du .mp3 par JAudiotagger !");
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Fichier inexistant !");
		}
	}
	/*
	public static void setTitle(Chanson c, String title) {
		File mp3 = new File(c.getNomFichier());
		try {
			//modification du tag du mp3
			AudioFile af = AudioFileIO.read(mp3);
			Tag tag = af.getTag();
			tag.setField(FieldKey.TITLE, title);
			af.commit();
			
			//Modification du titre de la chanson
			c.setTitre(title);
		} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			System.out.println("Erreur lors de l'ouverture du .mp3 par JAudioTagger !");
			e.printStackTrace();
		} catch (CannotWriteException e) {
			System.out.println("Erreur lors de l'écriture du .mp3 par JAudiotagger !");
			e.printStackTrace();
		}
	}
	
	public static void setAlbumTitle(Chanson c, String albumTitle) {
		File mp3 = new File(c.getNomFichier());
		try {
			//modification du tag mp3
			AudioFile af = AudioFileIO.read(mp3);
			Tag tag = af.getTag();
			tag.setField(FieldKey.ALBUM, albumTitle);
			af.commit();
			
			//Modification du titre de l'album
			Album a = new Album(albumTitle);
			if(c.getAlbum() != null) {
				a = c.getAlbum();
				a.setNom(albumTitle);
			}
			c.setAlbum(a);
		} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			System.out.println("Erreur lors de l'ouverture du .mp3 par JAudioTagger !");
			e.printStackTrace();
		} catch (CannotWriteException e) {
			System.out.println("Erreur lors de l'écriture du .mp3 par JAudiotagger !");
			e.printStackTrace();
		}
	}
	
	public static void setArtist(Chanson c, String artist) {
		File mp3 = new File(c.getNomFichier());
		try {
			//modification du tag mp3
			AudioFile af = AudioFileIO.read(mp3);
			Tag tag = af.getTag();
			tag.setField(FieldKey.ARTIST, artist);
			af.commit();
			
			//Modification du nom de l'artiste
			Artiste a = new Artiste(artist);
			if(c.getArtiste() != null) {
				a = c.getArtiste();
				a.setNom(artist);
			}
			c.setArtiste(a);
		} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			System.out.println("Erreur lors de l'ouverture du .mp3 par JAudioTagger !");
			e.printStackTrace();
		} catch (CannotWriteException e) {
			System.out.println("Erreur lors de l'écriture du .mp3 par JAudiotagger !");
			e.printStackTrace();
		}
	}
	
	public static void setGenre(Chanson c, String genre) {
		File mp3 = new File(c.getNomFichier());
		try {
			//modification du tag mp3
			AudioFile af = AudioFileIO.read(mp3);
			Tag tag = af.getTag();
			tag.setField(FieldKey.GENRE, genre);
			af.commit();
			
			//Modification du genre de la chanson
			// TODO gérer les genres par rapport à la liste de la bdd
			Genre g = new Genre(genre);
			c.setGenre(g);
		} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			System.out.println("Erreur lors de l'ouverture du .mp3 par JAudioTagger !");
			e.printStackTrace();
		} catch (CannotWriteException e) {
			System.out.println("Erreur lors de l'écriture du .mp3 par JAudiotagger !");
			e.printStackTrace();
		}
	}*/

}
