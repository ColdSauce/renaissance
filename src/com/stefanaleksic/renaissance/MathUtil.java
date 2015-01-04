package com.stefanaleksic.renaissance;

import java.util.ArrayList;

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


    //TODO: Figure out why this isn't working.
    //TODO: Document this code.
    public static boolean isOutlier(int index, ArrayList<Double> values){
        if(values.size() <= 1){
            System.out.println("values size less than one");
            return true;
        }
        //There are 2 edge cases one must account for. The first index and the last index.
        if(index == 0){
            System.out.println("checking right outlier");
            return isRightOutlier(index,values);
        }
        else if(index == values.size()-1){
            System.out.println("checking left outlier");
            return isLeftOutlier(index,values);
        }
        else{
            System.out.println("right: " + isRightOutlier(index,values));
            System.out.println("left: " + isLeftOutlier(index,values));
            return isRightOutlier(index,values) || isLeftOutlier(index,values);
        }
    }

    private static boolean isRightOutlier(int index, ArrayList<Double> values){
        double currentValue = values.get(index);
        double valueToRight = values.get(index + 1);
        return Math.abs(currentValue - valueToRight) > 20;
    }

    private static boolean isLeftOutlier(int index, ArrayList<Double> values){
        double currentValue = values.get(index);
        double valueToRight = values.get(index - 1);
        System.out.println(Math.abs(currentValue - valueToRight));
        return Math.abs(currentValue - valueToRight) > 20;
    }

    public static double getAverageOfList(ArrayList<Double> values){
        int amountOutliers = 0;
        double addedUp = 0;
        for(int index = 0; index < values.size();index++){
            if(!isOutlier(index,values)){
                amountOutliers++;
                System.out.println("the outlier was " + values.get(index));
                addedUp += values.get(index);
            }
        }
        return addedUp / (values.size() - amountOutliers);
    }

}
