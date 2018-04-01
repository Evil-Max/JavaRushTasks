package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(r.readLine()));
        r.close();
        int nWorld=0;
        while(br.ready())
            for(String c:br.readLine().split("\\W"))
                if ("world".equals(c)) nWorld++;
        br.close();
        System.out.println(nWorld);
    }
}
