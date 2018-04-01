package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader, -3));  //Hello Amigo

    }

    public static String decode(StringReader reader, int key) throws IOException {
        //char c,r;
        char c;
        int i;
        String res="";
        try {
            while ((i = reader.read()) != -1) {

                res += (char) (i + key);

/*
            r=c=(char)i;
            if (Character.isLetter(c)) {
                r = (char) (c + key);
                if (c == Character.toLowerCase(c)) {
                    if (r < 'a') r = (char) ('z' - ('a' - r) + 1);
                } else {
                    if (r < 'A') r = (char) ('Z' - ('A' - r) + 1);
                }
            };
            res+=r;
*/
            }
        } catch (Exception e) {

        }
        return res;
    }

}
