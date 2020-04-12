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
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.mapping;

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
        //albumsByArtist(SampleData.albums);
        //stringBuild(SampleData.threeArtists());
        //numberOfAlbum(SampleData.albums);
        //nameOfAlbum(SampleData.albums);
        //ex_2a();
        ex_2b();
    }

    private static Optional<Artist>biggestGroup(
            Stream<Artist>artists){
        Function<Artist,Long>getCount
                =artist -> artist.getMembers().count();
        return artists.collect(Collectors.maxBy(comparing(getCount)));
    }

    public static double averageNumberOfTracks(
            List<Album> albums){
        Double average = albums.stream().collect(
                Collectors.averagingDouble(
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

    private static void stringBuild(Stream<Artist>artists){
        String collect = artists.map(Artist::getName)
                .collect(Collectors
                        .joining(
                                ", "
                                ,"["
                                ,"]"));
        System.out.println(collect);
    }

    private static void numberOfAlbum(Stream<Album>albums){
        Map<Artist, Long> collect = albums.collect(
                Collectors.groupingBy(
                        album -> album.getMainMusician(),counting()));
        System.out.println(collect);
    }

    private static void nameOfAlbum(Stream<Album>albums){
        Map<Artist, List<String>> collect = albums.collect(
                Collectors.groupingBy(
                        Album::getMainMusician
                        , Collectors.mapping(
                                Album::getName, Collectors.toList())));
        System.out.println(collect);
    }

    private static void ex_2a(){
        Optional<String> collect = Stream.of("John Lennon"
                , "Paul McCartney"
                , "George Harrison"
                , "Ringo Starr"
                , "Pete Best"
                , "Stuart Sutcliffe"
        ).collect(
                Collectors.maxBy(
                        comparing(name -> name.length())));
        System.out.println(collect.get());
    }

    private static void ex_2b(){
        Stream<String>names=Stream.of(
                "John"
                , "Paul"
                , "George"
                , "John"
                , "Paul"
                , "John");
        Map<String, Long> collect = names.collect(
                Collectors.groupingBy(
                        name -> name, Collectors.counting()));
        System.out.println(collect);
    }
}
