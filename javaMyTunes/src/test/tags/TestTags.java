package test.tags;

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




public class TestTags {

	public static void main(String[] args) throws IOException, CannotReadException, org.jaudiotagger.tag.TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
		
		
		//TEST JAUDIOTAGGER
		File source = new File("/home/jim/Music/02 Dr. Feelgood.mp3");
		AudioFile f = AudioFileIO.read(source);
		Tag tag = f.getTag();
		System.out.println(tag.getFirst(FieldKey.GENRE));
		//tag.setField(FieldKey.TITLE, "OUI");
		//f.commit();
	}

}
