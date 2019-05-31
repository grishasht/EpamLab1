package song;

public class Song {
    private String name;
    private String author;
    private Float duration;
    private String genre;

    public Song(String name, String author, Float duration, String genre) {
        this.name = name;
        this.author = author;
        this.duration = duration;
        this.genre = genre;
    }

    public Song() {
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public Float getDuration() {
        return this.duration;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Song song = (Song)o;
            return this.name.equals(song.name) && this.author.equals(song.author) && this.duration.equals(song.duration) && this.genre.equals(song.genre);
        } else {
            return false;
        }
    }
}
