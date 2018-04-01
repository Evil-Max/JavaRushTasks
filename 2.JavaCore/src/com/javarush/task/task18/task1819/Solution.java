package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/
import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream f1;
        FileOutputStream f1w;
        FileInputStream f2;
        byte[] buffer_1=null,buffer_2=null;

        int i=0;

        String fileName1 = r.readLine();
        String fileName2 = r.readLine();

        r.close();

        //f1=new FileOutputStream(fileName1,true);
        f1=new FileInputStream(fileName1);

        if (f1.available() >= 0) {
            //читаем весь 1 файл
            buffer_1 = new byte[f1.available()];
            f1.read(buffer_1);
        }
        f1.close();
        f2=new FileInputStream(fileName2);

        if (f2.available() >= 0) {
            //читаем весь 2 файл
            buffer_2 = new byte[f2.available()];
            f2.read(buffer_2);
        }
        f2.close();
        f1w=new FileOutputStream(fileName1,false);
        f1w.write(buffer_2);
        f1w.close();
        f1w=new FileOutputStream(fileName1,true);
        f1w.write(buffer_1);
        f1w.close();


    }
}
