package services;

import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import song.Song;

public class CreateIerarchi {
    private FileFunctions fileFunctions = FileFunctions.getInstance();
    private List<Song> songs = new LinkedList();

    public List<Song> getSongs() {
        return this.songs;
    }

    private Song getSong(String song) throws IOException {
        /*Parses gotten string and returns gotten song*/
        Song songInit = new Song();
        String buff = "";
        int k = 0;

        for(int i = 0; i < song.length(); ++i) {
            char ch = song.charAt(i);
            if (ch != '-') {
                buff = buff + ch;
            } else
                switch(k) {
                    case 0: songInit.setName(buff); buff = ""; ++k;break;
                    case 1: songInit.setAuthor(buff); buff = ""; ++k; break;
                    case 2: songInit.setDuration(Float.parseFloat(buff)); buff = ""; ++k; break;
                    case 3: songInit.setGenre(buff); buff = ""; ++k; break;
                    default:
                        throw new NoSuchObjectException("No such object");
                }
        }
        return songInit;
    }

    public List<Song> getSongsList(String filePath) throws IOException {
        /*Reads songs from file and pushes it into songs list*/
        fileFunctions.openFile(filePath);
        songs.clear();

        String song;
        while((song = this.fileFunctions.readFromFile()) != "") {
            songs.add(this.getSong(song));
        }

        fileFunctions.closeFile();
        return songs;
    }

    public List<Song> addSongToIerarchy(String name, String author, Float duration, String genre) {
        /*Adds new song to songs list*/
        songs.add(new Song(name, author, duration, genre));
        return songs;
    }

    public List<Song> printSongsList(String filePath) throws IOException {
        getSongsList(filePath);
        for(Song song: songs) {
            System.out.println(song.getName() + " " + song.getAuthor()
                    + " " + song.getDuration() + " " + song.getGenre());
        }
        return songs;
    }

}
