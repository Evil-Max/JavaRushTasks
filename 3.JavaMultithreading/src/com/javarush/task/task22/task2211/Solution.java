package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        String fileSrc = args[0];
        String fileDst = args[1];
        /*
        String fileSrc = "c:/tmp/1_1251.txt";
        String fileDst = "c:/tmp/1_utf8.txt";
        */
        Reader r=new BufferedReader(new InputStreamReader(new FileInputStream(fileSrc), Charset.forName("Windows-1251")));
        Writer w=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDst), Charset.forName("UTF-8")));

        char[] buffer = new char[1024];
        int cnt;
        while((cnt=r.read(buffer))>0) {
            w.write(buffer,0,cnt);
        }
        r.close();
        w.close();
    }
}
