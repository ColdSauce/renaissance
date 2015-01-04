package com.stefanaleksic.renaissance;

/**
 * Created by Stefan on 1/3/2015.
 */
public class FrequencyUtil {

    //This is the hertz a piano emits while playing the note a4.
    private static final int a4Hz = 400;

    //This is the key that a4 corresponds to in a 88 key piano.
    private static final int a4Key = 49;

    /**
     * This method implements the frequency from n-th key found on Wikipedia.
     * To check it out for yourself, see this handy Wikipedia article.
     * https://en.wikipedia.org/wiki/Piano_key_frequencies
     *
     * @param n The key from 1 to 88 (piano)
     * @return The frequency from that key.
     */
    private double getFrequencyOfNthKey(int n) {
        double firstTerm = Math.pow(2, 1 / 12);
        firstTerm = Math.pow(firstTerm, n - 49);
        double secondTerm = a4Hz;
        return firstTerm * secondTerm;
    }

    /**
     * This method implements the frequency to n-th key found on Wikipedia.
     * To check it out for yourself, see this handy Wikipedia article.
     * https://en.wikipedia.org/wiki/Piano_key_frequencies
     *
     * @param frequency The frequency to convert to a key.
     * @return The key from 1 to 88 (piano)
     */
    private int getKeyFromFrequency(double frequency) {
        final int BASE_TWO = 2;
        double firstTerm = 12;
        double secondTerm = MathUtil.logOfBase(frequency / a4Hz, BASE_TWO);
        return (int) ((firstTerm * secondTerm) + a4Key);
    }
}
