package MusicMachine;

import java.util.*;
import java.util.stream.*;

public class SongSortTester {
    public static void main(String[] args) {
        SongSearch songSrch = new SongSearch();
        songSrch.showFirstFive();
        songSrch.searchArtist("The beatles");
        songSrch.searchArtist("The Beach Boys");
        //songSrch.anySongGnere("rock");
        //songSrch.songMaxPlayed();
    }
}

class SongSearch {
    private final List<SongAdv> songsAdv = MusicMachineData.SongsAdv.getSongsAdv();

    void showFirstFive() {
        List<String> firstFive = songsAdv.stream()
                                            .sorted(Comparator.comparingInt(SongAdv::getPlaysNum))
                                            .limit(5)
                                            .map(song -> String.join(" - ", song.getTitle(), song.getArtist()))
                                            .collect(Collectors.toList());
        System.out.println(firstFive);
    }

    void searchArtist(String artist) {
        Optional<SongAdv> resultArtis = songsAdv.stream()
                                                    .filter(song -> song.getArtist().equalsIgnoreCase(artist))
                                                    .findFirst();
        
        if (resultArtis.isPresent()) {
            System.out.println(resultArtis.get().getTitle());
        } else {
            System.out.println("Songs not found: " + artist);
        }
    }

    void anySongGnere(String gnere) {
        long count = songsAdv.stream()
                                .map(SongAdv::getGenre)
                                .filter(element -> element.equalsIgnoreCase(gnere))
                                .count();        
        
        boolean result = songsAdv.stream()
                                    .anyMatch(song -> song.getGenre().equalsIgnoreCase(gnere));
        if (result) {
            System.out.println("Songs of gnere: " + gnere + " was played, times: " + count);
        } else {
            System.out.println("There's no songs of gnere: " + gnere);
        }
    }

    void songMaxPlayed() {
        Optional<SongAdv> songMax = songsAdv.stream()
                                                .max(Comparator.comparingInt(SongAdv::getPlaysNum));

        if (songMax.isPresent()) {
            System.out.println("Song played most many times: " + String.join(" - ", songMax.get().getTitle(), songMax.get().getArtist()));
        } else {
            System.out.println("All songs was played equal number of times");
        }
    }
}