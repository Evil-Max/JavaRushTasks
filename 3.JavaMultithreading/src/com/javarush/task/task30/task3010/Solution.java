package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        try {
            String radixChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String input = args[0];
            //String input = "12AS08z";
            int max=-1;
            for(int i=0;i<input.length();i++) {
                    char c = Character.toUpperCase(input.charAt(i));
                    int ind = radixChar.indexOf(c);
                    //System.out.println(c+"="+ind);
                    if (ind == -1) {
                        System.out.println("incorrect");
                        max = -1;
                        break;
                    }
                    if (ind > max) max = ind;
            }
            if (max>=0) {
                if (max==0) max=1;
                System.out.println(max+1);
            }
        } catch (Exception e) {};
    }
}