package com.insightfullogic.java8.answers.chapter4;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.SampleData;

import java.util.IntSummaryStatistics;

/**
 * Created by serg on 04.04.20.
 */
public class Chapter04_my {

    public static void main(String[] args) {
        printTrackLenghtStatistics(SampleData.aLoveSupreme);
    }

    private static void printTrackLenghtStatistics(Album album){

        IntSummaryStatistics intSummaryStatistics
                = album.getTracks()
                .mapToInt(track ->{
                                System.out.println(track.getLength());
                        return track.getLength();
                })
                .summaryStatistics();
        System.out.println(intSummaryStatistics.getMax()
            +" - " + intSummaryStatistics.getMin()
                +" - " + intSummaryStatistics.getAverage()
                +" - " + intSummaryStatistics.getSum());

    }
    
}
