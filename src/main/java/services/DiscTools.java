package services;

import disc.Disc;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import song.Song;

public class DiscTools {
    private Disc disc;
    private HierarchyCreator ierarchi = new HierarchyCreator();
    private FileServices fileFunctions = new FileServices();
    private String filePath;

    public DiscTools(Disc disc, String filePath) {
        this.disc = disc;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    /*Records songs from file on disc*/
    public void writeSongsOnDisc() throws IOException {
        disc.writeOnDisc(this.ierarchi.getSongsList(this.filePath));
    }

    public void removeFromDisc(String songName) {
        List<Song> songs = disc.getSongs();
        songs.removeIf((song) -> song.getName().equals(songName));
        disc.writeOnDisc(songs);
    }

    /*Adds new users song to songs list on the disc*/
    public List<Song> addSongOnDisc(String name, String author, Float duration, String genre) throws IOException {
        ierarchi.addSongToIerarchy(name, author, duration, genre);
        fileFunctions.writeInFile(filePath, ierarchi.getSongs());
        writeSongsOnDisc();
        return disc.getSongs();
    }

    /*Removes song by name from disc*/
    public List<Song> removeSongFromDisc(String songName) throws IOException {
        removeFromDisc(songName);
        fileFunctions.writeInFile(filePath, disc.getSongs());
        return disc.getSongs();
    }

    public void printSongs(List<Song> songs) {
        if (songs != null)
            songs.forEach(song -> System.out.printf("%s -- %s -- %s -- %s",
                    song.getName(), song.getAuthor(), song.getDuration(), song.getGenre()));
    }

    /*Returns general songs duration from disc*/
    public Float getFullDuration() {
        List<Song> songs = disc.getSongs();
        Float fullDuration = 0.0F;
        if (songs != null) {
            songs.stream()
                    .mapToDouble(Song::getDuration)
                    .sum();
        }
        return fullDuration;
    }

    /*Returns list of songs from entered by user range*/
    public List<Song> songsByDuration(Integer from, Integer to) {
        return disc.getSongs().stream().filter(song -> song.getDuration() <= (float) to
                && song.getDuration() >= (float) from)
                .collect(Collectors.toList());
    }

    /*Sorts information on disk by chosen parameter*/
    public List<Song> sortSongs(String sortBy) {
        sortBy = sortBy.toUpperCase();
        switch (sortBy) {
            case "NAME":
                return Sort.sortByName(this.disc);
            case "AUTHOR":
                return Sort.sortByAuthor(this.disc);
            case "DURATION":
                return Sort.sortByGenre(this.disc);
            case "GENRE":
                return Sort.sortByDuration(this.disc);
            default:
                return null;
        }
    }
}
