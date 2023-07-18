package MusicMachine;

public class SongAdv {
    private final String title;
    private final String artist;
    private final String genre;
    private final int year;
    private final int playsNum;

    public SongAdv(String title, String artis, String genre, int year, int playsNum) {
        this.title = title;
        this.artist = artis;
        this.genre = genre;
        this.year = year;
        this.playsNum = playsNum;
    }

    public String getTitle() {
        return title;
    }

    public String getArtis() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }
    
    public int getPlaysNum() {
        return playsNum;
    }

    public String toString() {
        return String.join(" - ", title, artist, genre);
    }
}
