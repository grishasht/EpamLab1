package services;

import disc.Disc;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import song.Song;

public class Sort {
    public Sort() {
    }

    public static List<Song> sortByName(Disc disc) {
        return disc.getSongs().stream().sorted(Comparator
                .comparing(Song::getName)).collect(Collectors.toList());
    }

    public static List<Song> sortByAuthor(Disc disc) {
        return disc.getSongs().stream().sorted(Comparator
                .comparing(Song::getAuthor)).collect(Collectors.toList());
    }

    public static List<Song> sortByDuration(Disc disc) {
        return disc.getSongs().stream().sorted(Comparator
                .comparing(Song::getDuration)).collect(Collectors.toList());
    }

    public static List<Song> sortByGenre(Disc disc) {
        return disc.getSongs().stream().sorted(Comparator
                .comparing(Song::getGenre)).collect(Collectors.toList());
    }
}
