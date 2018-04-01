package com.javarush.task.task19.task1906;

/* 
Четные символы
*/
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException  {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = r.readLine();
        String fileName2 = r.readLine();

        r.close();

        FileReader br = new FileReader(fileName1);
        FileWriter bw = new FileWriter(fileName2);
        // чтение посимвольно
        int c,i=1;
        while(br.ready()){
            c=br.read();
            if (i%2==0) {
                bw.write(c);
            }
            i++;
        }
        br.close();
        bw.close();

    }
}
