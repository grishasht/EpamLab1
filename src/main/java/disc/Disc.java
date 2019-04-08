package disc;

import java.util.List;
import java.util.function.Predicate;
import song.Song;

public class Disc {
    private List<Song> songs;

    public Disc() {
    }

    public void writeOnDisc(List<Song> songs) {
        this.songs = songs;
    }

    public void removeFromDisc(String songName) {
        this.songs.removeIf((song) -> song.getName().equals(songName));
    }

    public List<Song> getSongs() {
        return this.songs;
    }

}
