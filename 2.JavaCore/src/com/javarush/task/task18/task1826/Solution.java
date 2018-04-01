package com.javarush.task.task18.task1826;

/* 
Шифровка
*/
/*
-e fileName fileOutputName
-d fileName fileOutputName
 */

import java.io.*;

public class Solution {

    public static void main(String[] args) throws IOException {

        String fileName=args[1];
        String fileOutputName=args[2];
        String sKey=args[0];

        /*
        String fileName="c:/tmp/0e.txt";
        String fileOutputName="c:/tmp/0d.txt";
        String sKey="-d";
        */

        byte[] buffer=null;


        if (("-d".equals(sKey)) || ("-e".equals(sKey))) {

            FileInputStream fIn = new FileInputStream(fileName);
            FileOutputStream fOut = new FileOutputStream(fileOutputName);

            if (fIn.available() >= 0) {
                buffer = new byte[fIn.available()];
                fIn.read(buffer);
            }
            for (int i = 0; i < buffer.length; i++) {
                buffer[i]^= (byte)0xAA;
            }
            fOut.write(buffer);

            fIn.close();
            fOut.close();
        }

    }

}
