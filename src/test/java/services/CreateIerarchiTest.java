//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package services;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import song.Song;

public class CreateIerarchiTest {
    CreateIerarchi ierarchi = new CreateIerarchi();

    @Test
    public void getSongsListShouldReturnCorrectList() throws IOException {
        List<Song> result = this.ierarchi.getSongsList("src/main/resources/testFile.txt");
        List<Song> expected = new LinkedList();
        expected.add(new Song("Secrets", "One Republic", 3.78F, "Rock"));

        Assert.assertEquals(expected, result);
    }

    @Test
    public void addSongToIerarchyShouldReturnCorrect() {
        List<Song> result = this.ierarchi.addSongToIerarchy("Rustem", "Strykalo", 4.0F, "Rock");
        List<Song> expected = new LinkedList();
        expected.add(new Song("Rustem", "Strykalo", 4.0F, "Rock"));

        Assert.assertEquals(expected, result);
    }
}
