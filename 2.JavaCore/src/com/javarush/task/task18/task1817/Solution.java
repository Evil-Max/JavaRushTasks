package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length==0) return;
        String fileName = args[0];
        //String fileName = "c:/tmp/3.txt";
        int numSpaces=0,numBytes=0;
        char letter;

        FileInputStream f = new FileInputStream(fileName);
        //numBytes=f.available();
        while (f.available()>0) {
            letter=(char)f.read();
            if (letter==' ') numSpaces++;
            numBytes++;
        }

        f.close();
        System.out.println(String.format("%.2f",((float)numSpaces/numBytes)*100));
    }
}
