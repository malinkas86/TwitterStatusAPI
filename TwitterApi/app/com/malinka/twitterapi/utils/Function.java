package com.malinka.twitterapi.utils;

/**
 * Created by malinkas on 5/18/16.
 */
public class Function {
    public static String generate9DigitRandomNumber(){
        long timeSeed = System.nanoTime(); // to get the current date time value

        double randSeed = Math.random() * 1000; // random number generation

        long midSeed = (long) (timeSeed * randSeed); // mixing up the time and
        // rand number.

        // variable timeSeed
        // will be unique


        // variable rand will
        // ensure no relation
        // between the numbers

        String s = midSeed + "";
        return s.substring(0, 9);
    }
}
