package com.javarush.task.task30.task3009;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }
    private static Set<Integer> getRadix(String number) {
        /*
        BigInteger a = new BigInteger(number.getDigit(),number.getNumerationSystem().getNumerationSystemIntValue());
        String result = a.toString(expectedNumerationSystem.getNumerationSystemIntValue());
        return new Number(expectedNumerationSystem,result);
         */
        Set<Integer> result = new HashSet<>();
        for(int i=2;i<=36;i++) {
            try {
                String poly = (new BigInteger(number)).toString(i);
                String polyLeft = poly.substring(0, (poly.length() + 1) / 2);
                String polyRight = poly.substring((poly.length() ) / 2 );
                //System.out.println(i+":"+poly+" "+polyLeft+"="+polyRight);
                if (polyLeft.equalsIgnoreCase((new StringBuffer(polyRight)).reverse().toString())) {
                    result.add(i);
                }
            } catch (Exception e) {};
        }
        return result;
    }
}