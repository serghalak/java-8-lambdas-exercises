package com.insightfullogic.java8.answers.chapter3;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import com.insightfullogic.java8.examples.chapter1.Track;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toSet;

public class Chapter03_my {

    public static void main(String[] args) {
//        List<Artist> allArtists = getAllArtists();
//        System.out.println(allArtists.size());
//        long count=allArtists.stream().filter(
//                artist -> {
//                    System.out.println(artist.getName()+": " + artist.getNationality());
//                    return artist.isFrom("UK");
//                }
//        ).count();
//        System.out.println("count: " + count);
        //lCollect();
        //lMap();
        //lFilter();
        //lFlatMap();
        //min_max();
        //lreduce();
        //all_nationality();

        Ex3_7();
        //countLowerCase();
    }

    private static List<Artist>getAllArtists(){
        List<Artist> allArtists = SampleData.membersOfTheBeatles;
        List<Artist> artists = new ArrayList<>(allArtists);
        artists.add(SampleData.johnColtrane);
        return artists;
    }
    private static void lCollect(){
        List<String> collected= Stream.of("a","b","c")
                .collect(Collectors.toList());
        System.out.println(collected);

    }
    private static void lMap(){
//        List<String>list=new ArrayList<String>();
//        list.add("a");
//        list.add("b");
//        list.add("hello");
        List<String>list=Stream.of("a","b","hello")
                .map(str->str.toUpperCase()).collect(Collectors.toList());

//        List<String> collect = list.stream().map(el -> el.toUpperCase())
//                .collect(Collectors.toList());
        System.out.println(list );
    }
    private static void lFilter(){
        List<String>list=Stream.of("a","1abs","abc1")
                .filter(str->isDigit(str.charAt(0)))
                .collect(Collectors.toList());
        System.out.println(list);
    }
    private static void lFlatMap(){
        List<Integer>together=Stream.of(
                Arrays.asList(1,2)
                ,Arrays.asList(3,4))
                .flatMap(streamNumb->streamNumb.stream())
                .collect(Collectors.toList());
        System.out.println(together);
    }
    private static void min_max(){
        List<Track>tracks=Arrays.asList(
                new Track("Bakai",524)
                ,new Track("Violets for Yuor Furs",378));

        Track shortestTrack=tracks.stream()
                .min(Comparator.comparing(track ->track.getLength()))
                .get();
        Track longestTrack= tracks.stream()
                .max(Comparator.comparing(track ->track.getLength()))
                .get();
        System.out.println("min="+shortestTrack.getName()+'\n'
                +"max="+longestTrack.getName());
    }
    private static void lreduce(){
        Integer sum =
                Stream.of(1, 2, 3 )
                        .reduce(0, (acc, element) -> acc + element);
        System.out.println("sum="+sum);
    }
    private static void all_nationality(){
        Album album = SampleData.aLoveSupreme;
        List<String> origins = album.getMusicians()
                .filter(artist -> {
                    System.out.println(artist.getName());
                    //artist.getName().startsWith("The")
                    return true;
                })
                .map(artist -> artist.getNationality())
                .collect(Collectors.toList());
        System.out.println(origins);

    }
    private static void Ex3_7(){
        List<String>list=Arrays.asList("Serg","Volodimir","MiSha");
        String s = list.stream()
                .max(Comparator.comparing(Chapter03_my::countLowerCase))
                .get();


        System.out.println(s);
    }
    private static long countLowerCase(String name){
        //String name="SeRgiy";
        long count = name.chars().filter(Character::isLowerCase).count();
        System.out.println(count);
        return count;
    }

}
