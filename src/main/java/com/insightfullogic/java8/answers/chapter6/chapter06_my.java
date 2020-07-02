package com.insightfullogic.java8.answers.chapter6;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import com.insightfullogic.java8.examples.chapter1.Track;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by serg on 18.04.20.
 */
public class chapter06_my {

    public static void main(String[] args) {
        //serial();
        parallel();
    }

    private static void serial(){
        int sum = SampleData.albums.flatMap(Album::getTracks)
                .mapToInt(Track::getLength).sum();

        System.out.println("sum = " + sum);
    }

    private static void parallel(){
        int sum = SampleData.albums
                .collect(Collectors.toList())
                .parallelStream().flatMap(Album::getTracks)
                .mapToInt(Track::getLength)
                .sum();

        System.out.println("parralel sum: " + sum);
    }
}
