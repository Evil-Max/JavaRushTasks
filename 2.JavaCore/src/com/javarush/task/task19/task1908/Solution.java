package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileNameR=r.readLine();
        String fileNameW=r.readLine();
        //String fileNameR="c:/tmp/w.txt";
        //String fileNameW="c:/tmp/w1.txt";
        r.close();

        BufferedReader br = new BufferedReader(new FileReader(fileNameR));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileNameW));

        while(br.ready()) {
            for(String s:br.readLine().split("\\s")) {
                try {
                    int x = Integer.parseInt(s);
                    bw.write(s+" ");
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
        br.close();
        bw.close();
    }
}
