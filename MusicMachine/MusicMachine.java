package MusicMachine;

import java.util.*;

public class MusicMachine {
    public static void main(String[] args) {
        new MusicMachine().doIt();
    }
    public void doIt() {
        List<String> songList = MockSong.getSongs();
        System.out.println(songList);
        Collections.sort(songList);
        System.out.println(songList);

        List<SongV2> songV2List = MockSong.getSongsV2();
        System.out.println(songV2List);
        //System.out.println(songV2List.get(0));
        Collections.sort(songV2List);
        System.out.println(songV2List);
    }
}

class SongV2 implements Comparable<SongV2> {
    private String title;
    private String artist;
    private int lenght;

    public int compareTo(SongV2 s) {
        return title.compareTo(s.getTitle());
    }

    SongV2(String title, String artist, int lenght) {
        this.title = title;
        this.artist = artist;
        this.lenght = lenght;
    }
    public String getTitle() {
        return title;
    }
    public String getArtist() {
        return artist;
    }
    public int getLenght() {
        return lenght;
    }
    public String toString() {
        return title;
    }
}

class MockSong {
    public static List<String> getSongs() {
        List<String> songs = new ArrayList<>();

        songs.add("Liebestraum No. 3");
        songs.add("Hungarian Rhapsody No. 2");
        songs.add("Traumerei");
        songs.add("2nd Piano Concerto");
        songs.add("Nocturne op. 27 No. 2");
        songs.add("Paganini Variations");
        songs.add("2nd Piano Concerto");
        return songs;
    }

    public static List<SongV2> getSongsV2() {
        List<SongV2> songs = new ArrayList<>();

        songs.add(new SongV2("Liebestraum No. 3", "Liszt", 290));
        songs.add(new SongV2("Hungarian Rhapsody No. 2", "Liszt", 541));
        songs.add(new SongV2("Traumerei", "Schumann", 201));
        songs.add(new SongV2("2nd Piano Concerto", "Chopin", 583));
        songs.add(new SongV2("Nocturne op. 27 No. 2", "Chopin", 371));
        songs.add(new SongV2("Paganini Variations", "Rachmaninov", 168));
        songs.add(new SongV2("2nd Piano Concerto", "Beethoven", 554));
        return songs;
    }
}