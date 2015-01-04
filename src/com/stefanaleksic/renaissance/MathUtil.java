package com.stefanaleksic.renaissance;

/**
 * Created by Stefan on 1/3/2015.
 */
public class MathUtil {

    /**
     *
     * @param number
     * The number one wants to evaluate the log base at.
     * @param base
     * The base to evaluate the log at.
     * @return
     * The value given by the log of a base.
     */
    public static double logOfBase(double number, int base){
        return Math.log(number) / Math.log(base);
    }

}
