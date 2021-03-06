package tagEdit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

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
import org.jaudiotagger.tag.reference.GenreTypes;

import dao.DaoAlbum;
import dao.DaoArtiste;
import dao.DaoGenre;
import dao.jpa.DaoAlbumJPA;
import dao.jpa.DaoArtisteJPA;
import dao.jpa.DaoChansonJPA;
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
	
	private static String serverPath = "/home/jim/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/javaMyTunes/resources/audio/";
	
	public static Chanson importChanson(String path) {
		// TODO déplacer le mp3 dans le dossier choisi
		// TODO modifier la fonction pour prendre en paramètre un objet chanson
		Chanson c = new Chanson();
		
		DaoAlbum daoAlbum = DaoAlbumJPA.getInstance();
		DaoArtiste daoArtiste = DaoArtisteJPA.getInstance();
		DaoGenre daoGenre = DaoGenreJPA.getInstance();
		
		Path sourcePath = Path.of(path);
		Path destPath = Path.of(serverPath+sourcePath.getFileName());
		System.out.println(sourcePath.getFileName().toString());
		try {
			Files.copy(sourcePath, destPath, StandardCopyOption.COPY_ATTRIBUTES);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		File mp3 = new File(path);
		if(mp3.exists()) {
			c.setNomFichier(sourcePath.getFileName().toString());
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
					//Le tag du genre est sous la forme "(idGenre)" il faut donc enlever les parenthèse
					String tagGenre = tag.getFirst(FieldKey.GENRE);
					tagGenre = tagGenre.replace("(", "");
					tagGenre = tagGenre.replace(")", "");
					//System.out.println(tagGenre);
					int idGenre = Integer.valueOf(tagGenre);
					Genre genre  = daoGenre.get(idGenre+1); //+1 car les genres commencent à partir de 1 sur la bdd et non 0
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
	
	//songName = nom avant modification de la chanson, dans le cas si on change le nom du fichier .mp3
	public static void exportTag(Chanson c, String songName) {
		//on récupère le nom original du fichier avant de le renommer
		System.out.println("Original Name: " + songName);
		System.out.println(c);
		File mp3 = new File(serverPath+songName);
		if(mp3.exists()) {
			try {
				AudioFile af = AudioFileIO.read(mp3);
				Tag tag = af.getTag();
				//maj des tags
				//tag titre
				if(c.getTitre() != null)
					tag.setField(FieldKey.TITLE, c.getTitre());
				else
					tag.setField(FieldKey.TITLE, "");
				
				//tag nom artiste
				if(c.getArtiste() != null)
					tag.setField(FieldKey.ARTIST, c.getArtiste().getNom());
				else
					tag.setField(FieldKey.ARTIST, "");
				
				//tag nom album
				if(c.getAlbum() != null)
					tag.setField(FieldKey.ALBUM, c.getAlbum().getNom());
				else
					tag.setField(FieldKey.ALBUM, "");
				
				//tag genre
				if(c.getGenre() != null) {
					String genre = "(" + (c.getGenre().getId()-1) + ")";
					tag.setField(FieldKey.GENRE, genre); //Le tag du genre est sous la forme "(idGenre)" il faut donc ajouter les parenthèse et -1 par rappoert à la bdd
				}
				else
					tag.setField(FieldKey.GENRE, "");
				
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
			mp3.renameTo(new File(serverPath+c.getNomFichier()));
		}
		else {
			System.out.println("Fichier inexistant !");
		}
	}
	
	//permet de supprimer une chanson sur le serveur
	public static void removeChanson(Chanson chanson) {
		File mp3 = new File(serverPath+chanson.getNomFichier());
		if(mp3.exists()) {
			mp3.delete();
		}
	}
}
