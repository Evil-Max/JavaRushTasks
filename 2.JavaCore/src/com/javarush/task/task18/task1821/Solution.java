package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length==0) return;
        String fileName = args[0];
        //String fileName = "c:/tmp/1.txt";
        byte[] buffer=null;
        int[] cnt = new int[256];

        for(int i=0;i<256;i++) cnt[i]=0;

        FileInputStream f = new FileInputStream(fileName);
        if (f.available() >= 0) {
            buffer = new byte[f.available()];
            f.read(buffer);
        }
        f.close();
        for(int i=0;i<buffer.length;i++) {
            cnt[buffer[i]]++;
        }
        for (int i=0;i<256;i++) {
            if (cnt[i]>0) {
                System.out.println((char)i+" "+cnt[i]);
            }
        }
    }
}
