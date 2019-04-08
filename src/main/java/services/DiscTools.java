package services;

import disc.Disc;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import song.Song;

public class DiscTools {
    private Disc disc;
    private CreateIerarchi ierarchi = new CreateIerarchi();
    private FileFunctions fileFunctions = FileFunctions.getInstance();
    private String filePath;

    public DiscTools(Disc disc, String filePath) {
        this.disc = disc;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void writeSongsOnDisc() throws IOException {
        /*Records songs from file on disc*/
        disc.writeOnDisc(this.ierarchi.getSongsList(this.filePath));
    }

    public List<Song> addSongOnDisc(String name, String author, Float duration, String genre) throws IOException {
        /*Adds new users song to songs list on the disc*/
        ierarchi.addSongToIerarchy(name, author, duration, genre);
        fileFunctions.writeInFile(filePath, ierarchi.getSongs());
        writeSongsOnDisc();
        return disc.getSongs();
    }

    public List<Song> removeSongFromDisc(String songName) throws IOException {
        /*Removes song by name from disc*/
        disc.removeFromDisc(songName);
        fileFunctions.writeInFile(filePath, disc.getSongs());
        return disc.getSongs();
    }

    public void printSongs(List<Song> songs) throws IOException {
        if (songs != null)
            for(Song song: songs)
                System.out.println(song.getName() + " -- " + song.getAuthor()
                        + " -- " + song.getDuration() + " -- " + song.getGenre());
    }

    public Float getFullDuration() {
        /*Returns general songs duration from disc*/
        List<Song> songs = disc.getSongs();
        Float fullDuration = 0.0F;
        Song song;
        if (songs != null) {
            for(Iterator iter = songs.iterator(); iter.hasNext(); fullDuration = fullDuration + song.getDuration()) {
                song = (Song)iter.next();
            }
        }

        return fullDuration;
    }

    public List<Song> songsByDuration(Integer from, Integer to) {
        /*Returns list of songs from entered by user range*/
        return disc.getSongs().stream().filter(song -> song.getDuration() <=
                (float)to && song.getDuration() >= (float)from).collect(Collectors.toList());
    }

    public List<Song> sortSongs(String sortBy) {
        /*Sorts information on disk by chosen parameter*/
        sortBy = sortBy.toUpperCase();
        switch(sortBy) {
            case "NAME": return Sort.sortByName(this.disc);
            case "AUTHOR": return Sort.sortByAuthor(this.disc);
            case "DURATION": return Sort.sortByGenre(this.disc);
            case "GENRE": return Sort.sortByDuration(this.disc);
            default: return null;
        }
    }
}
