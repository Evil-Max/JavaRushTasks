package com.javarush.task.task19.task1922;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName = r.readLine();
        //String fileName = "c:/tmp/w.txt";
        r.close();
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String s,s1;
        String[] line;
        int cnt;


        while ((s=br.readLine())!=null) {

            line = s.split(" ");
            cnt=0;
            for(String c:words) {

                for(int i=0;i<line.length;i++) {
                    if (c.equalsIgnoreCase(line[i])) {
                        cnt++;
                        break;
                    }
                }

            }
            if (cnt==2) {
                System.out.println(s);
            }
        }

        br.close();
    }
}
