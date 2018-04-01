package com.javarush.task.task18.task1818;

/* 
Два в одном
*/
import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        FileOutputStream f1;
        FileInputStream f2;
        FileInputStream f3;
        int i=0;

        String fileName1 = r.readLine();
        String fileName2 = r.readLine();
        String fileName3 = r.readLine();
        r.close();

        f1=new FileOutputStream(fileName1,true);
        f2=new FileInputStream(fileName2);
        f3=new FileInputStream(fileName3);

        if (f2.available() >= 0) {
            //читаем весь файл одним куском
            byte[] buffer = new byte[f2.available()];
            f2.read(buffer);
            f1.write(buffer);
        }
        if (f3.available() >= 0) {
            //читаем весь файл одним куском
            byte[] buffer = new byte[f3.available()];
            f3.read(buffer);
            f1.write(buffer);
        }



        f1.close();
        f2.close();
        f3.close();

    }
}
