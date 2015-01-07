package com.stefanaleksic.renaissance;

import java.util.ArrayList;

/**
 * Created by Stefan on 1/3/2015.
 */
public class MathUtil {

    /**
     * Gets the log at a certain base of a number.
     *
     * @param number The number one wants to evaluate the log base at.
     * @param base   The base to evaluate the log at.
     * @return The value given by the log of a base.
     */
    public static double logOfBase(double number, int base) {
        return Math.log(number) / Math.log(base);
    }

    //TODO: Make this much better. This is a really naive way of doing it.

    /**
     * This method looks at the data set and if there is a value that is 20 numbers higher than either the left, it
     * is an outlier.
     *
     * @param index  The index to test.
     * @param values The data set.
     * @return
     */
    public static boolean isOutlier(int index, ArrayList<Double> values) {
        if (values.size() <= 1) {
            return true;
        }
        //There are 2 edge cases one must account for. The first index and the last index.
        if (index == 0) {
            return isRightOutlier(index, values);
        } else if (index == values.size() - 1) {
            return isLeftOutlier(index, values);
        } else {
            return isRightOutlier(index, values) || isLeftOutlier(index, values);
        }
    }

    /**
     * This is simply just a utility method for isOutlier
     *
     * @param index
     * @param values
     * @return
     */
    private static boolean isRightOutlier(int index, ArrayList<Double> values) {
        double currentValue = values.get(index);
        double valueToRight = values.get(index + 1);
        return Math.abs(currentValue - valueToRight) > 20;
    }

    /**
     * This is simply just a utility method for isOutlier
     *
     * @param index
     * @param values
     * @return
     */
    private static boolean isLeftOutlier(int index, ArrayList<Double> values) {
        double currentValue = values.get(index);
        double valueToLeft = values.get(index - 1);
        return Math.abs(currentValue - valueToLeft) > 20;
    }

    /**
     * Gets the average of a list of values semi-intelligently by removing outliers.
     *
     * @param values List of double values to get outlers from.
     * @return The average from the data set.
     */
    public static double getAverageOfList(ArrayList<Double> values) {
        int amountOutliers = 0;
        double addedUp = 0;
        for (int index = 0; index < values.size(); index++) {
            if (isOutlier(index, values)) {
                amountOutliers++;
                addedUp += values.get(index);
            }
        }
        return addedUp / (values.size() - amountOutliers);
    }


}
