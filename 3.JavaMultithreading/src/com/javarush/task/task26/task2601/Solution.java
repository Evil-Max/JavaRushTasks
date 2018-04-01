package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        /*
        Integer[] array = {2,3,1,3,6,5,0};
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(sort(array)));
        */
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        if (array.length>1) {
            Arrays.sort(array);
            int median = (array.length % 2 == 0) ? ((array[array.length / 2] + array[array.length / 2-1])/2) : (array[array.length/2]);
            //System.out.println(median);
            Arrays.sort(array, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    int a1=Math.abs(o1-median);
                    int a2=Math.abs(o2-median);
                    return (a1==a2)?(o1-o2):(a1-a2);
                }
            });
        }
        return array;
    }
}
