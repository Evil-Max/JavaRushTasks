package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream f1;
        FileOutputStream f2;

        byte[] buffer=null;
        StringBuffer sb = new StringBuffer();

        String fileName1 = r.readLine();
        String fileName2 = r.readLine();

        r.close();
        f1=new FileInputStream(fileName1);
        if (f1.available() >= 0) {
            buffer = new byte[f1.available()];
            f1.read(buffer);
        }
        f1.close();
        for(String digit: (new String(buffer)).split(" ")) {
            sb.append(Math.round(Double.valueOf(Double.parseDouble(digit)))+" ");
        }

        f2=new FileOutputStream(fileName2);
        f2.write(sb.toString().getBytes());
        f2.close();

    }
}
