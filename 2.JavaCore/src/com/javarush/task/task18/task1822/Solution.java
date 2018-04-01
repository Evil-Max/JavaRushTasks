package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName = r.readLine();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String s;
        String data[];
        Integer id = Integer.parseInt(args[0]);
        //Integer id = 4;
        // c:\tmp\data.txt

        while((s=br.readLine())!=null) {
            data=s.split(" ");
            if (Integer.parseInt(data[0])==id) {
                System.out.println(s);
            }
        }
        br.close();
    }
}
