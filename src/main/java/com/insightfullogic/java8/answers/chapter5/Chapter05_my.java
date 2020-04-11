package com.insightfullogic.java8.answers.chapter5;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import com.insightfullogic.java8.examples.chapter3.Functional;

import java.util.List;
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
        double averageNumberOfTracks = averageNumberOfTracks(
                SampleData.albums.collect(Collectors.toList()));
        System.out.println(averageNumberOfTracks);
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
}
