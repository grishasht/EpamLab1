package services;

import disc.Disc;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import song.Song;

public class DiscToolsTest {
    private FileServices fileFunctions = new FileServices();
    private HierarchyCreator ierarchi = new HierarchyCreator();
    private Disc disc = new Disc();

    public Song resultSong(String name, String author, Float duration, String genre, String filePath) throws IOException {
        DiscTools discTools = new DiscTools(this.disc, filePath);
        List<Song> resultList = discTools.addSongOnDisc(name, author, duration, genre);
        Song resultSong = (Song)resultList.get(0);

        return resultSong;
    }

    public Song expectedSong(String name, String author, Float duration, String genre) {
        return new Song(name, author, duration, genre);
    }

    @Test
    public void shouldReturnTrueIfAddedSong() throws Exception {
        Boolean result = this.expectedSong("Apologize", "One Republic", 3.78F, "Rock").equals(this.resultSong("Apologize", "One Republic", 3.78F, "Rock", "src/main/resources/testFile.txt"));
        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfAddedSong() throws Exception {
        Boolean result = this.expectedSong("Apologize", "One Republic", 3.78F, "Rock").equals(this.resultSong("Secrets", "One Republic", 3.78F, "Rock", "src/main/resources/testFile.txt"));

        Assert.assertFalse(result);
    }

    @Test
    public void shouldReturnTrueIfSongWasRemoved() throws IOException {
        DiscTools discTools = new DiscTools(this.disc, "src/main/resources/testFile.txt");
        List<Song> resultList = discTools.addSongOnDisc("Secrets", "One Republic", 3.78F, "Rock");
        resultList = discTools.removeSongFromDisc("Apologize");
        List<Song> expectedList = this.ierarchi.getSongsList("src/main/resources/correctTestFile.txt");
        boolean result = resultList.containsAll(expectedList);

        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnCorrectSongDuration() {
        DiscTools discTools = new DiscTools(this.disc, "src/main/resources/testFile.txt");
        Float result = discTools.getFullDuration();
        Float expected = 0.0F;

        Assert.assertEquals(expected, result);
    }
}
