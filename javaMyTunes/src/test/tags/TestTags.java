package test.tags;

import java.io.File;
import java.io.IOException;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagConstant;
import org.farng.mp3.TagException;
import org.farng.mp3.TagOptionSingleton;
import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.ID3v1;
import org.farng.mp3.id3.ID3v2_4;

public class TestTags {

	public static void main(String[] args) throws IOException, TagException {
		// TODO Auto-generated method stub
		
		//MP3 CONTENANT DEJA DES TAGS
		File source = new File("/home/jim/Music/02 Dr. Feelgood.mp3");
		
		if(source.exists()) {
			MP3File mp3 = new MP3File(source);
			//TagOptionSingleton.getInstance().setDefaultSaveMode(TagConstant.MP3_FILE_SAVE_OVERWRITE);
			if(mp3.hasID3v2Tag()) {
				System.out.println(mp3.getID3v2Tag());
				//mp3.getID3v2Tag().setSongTitle("OUI");
				//mp3.getID3v2Tag().setAlbumTitle("NON");
				//mp3.save();
			}
			else {
				System.out.println("Ce mp3 n'a pas de tag ID3v2 !");
			}
		}
		else {
			System.out.println("fichier introuvable !");
		}
		
		/*
		//MP3 N AYANT PAS DE TAG
		File source = new File("/home/jim/Music/Beck - Debra.mp3");
		if(source.exists()) {
			MP3File mp3 = new MP3File(source);
			if(mp3.hasID3v2Tag()) {
				System.out.println(mp3.getID3v2Tag());
			}
			else {
				System.out.println("Ce mp3 n'a pas de tag ID3v2 !");
			}
		}
		else {
			System.out.println("fichier introuvable !");
		}*/
		
	}

}
