package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        int radix=10;
        String s1=s;
        if (s.length()>=2) {
            if (s.charAt(1)=='x')
                radix=16;
            else if ((s.charAt(1)=='b') && (s.charAt(0)=='0')) radix=2;
            else if (s.charAt(0)=='0') radix=8;
            if ((radix==16) | (radix==2)) s1=s.substring(2);
            else if (radix==8) s1=s.substring(1);
        }
        return String.valueOf(Integer.parseInt(s1,radix));
    }
}
