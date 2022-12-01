package com.developwithjon.utils;

import java.util.ArrayList;

public class ArrayUtils {

    public static int arrayMax(ArrayList...arrays){
        ArrayList<Object> maxArray = arrays[0];
        int maxArraySize = maxArray.size();
        for (ArrayList arr : arrays){
            if (arr.size() > maxArraySize){
                maxArraySize = arr.size();
            }
        }
        return maxArraySize;
    }

    public static double getArrayValue(ArrayList<Double> array, int index){
        try{
            return array.get(index);
        }
        catch (ArrayIndexOutOfBoundsException e){
            return 0;
        }
    }
}
