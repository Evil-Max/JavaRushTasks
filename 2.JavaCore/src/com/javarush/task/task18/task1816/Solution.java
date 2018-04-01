package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length==0) return;
        String fileName = args[0];
        //String fileName = "c:/tmp/1.txt";
        int engLetters=0;
        char letter;

        FileInputStream f = new FileInputStream(fileName);
        while (f.available()>0) {
            letter=(char)f.read();
            if (String.valueOf(letter).matches("[A-Za-z]")) engLetters++;
        }

        f.close();
        System.out.println(engLetters);
    }
}
