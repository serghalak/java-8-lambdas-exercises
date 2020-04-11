package com.insightfullogic.java8.answers.chapter5;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import com.insightfullogic.java8.examples.chapter3.Functional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

/**
 * Created by serg on 11.04.20.
 */
public class Chapter05_my {

    public static void main(String[] args) {
//        Optional<Artist> artist = biggestGroup(SampleData.threeArtists());
//        System.out.println(artist.get().getMembers().count());
//        double averageNumberOfTracks = averageNumberOfTracks(
//                SampleData.albums.collect(Collectors.toList()));
//        System.out.println(averageNumberOfTracks);
        //bandsAndSolo(SampleData.threeArtists());
        albumsByArtist(SampleData.albums);
    }

    private static Optional<Artist>biggestGroup(
            Stream<Artist>artists){
        Function<Artist,Long>getCount
                =artist -> artist.getMembers().count();
        return artists.collect(Collectors.maxBy(comparing(getCount)));
    }

    public static double averageNumberOfTracks(
            List<Album> albums){
        Double average = albums.stream().collect(Collectors.averagingDouble(
                album -> album.getTrackList().size()
        ));
        return average;

    }

    public static Map<Boolean,List<Artist>>bandsAndSolo(
      Stream<Artist>artists){
        Map<Boolean, List<Artist>> collect =
                artists.collect(
                        Collectors.partitioningBy(Artist::isSolo));
        System.out.println(collect);
        return collect;
    }

    private static Map<Artist,List<Album>>
        albumsByArtist(Stream<Album>albums){
        Map<Artist, List<Album>> collect = albums.collect(
                Collectors.groupingBy(
                        album -> album.getMainMusician()));
        System.out.println(collect);
        return collect;
    }
}
