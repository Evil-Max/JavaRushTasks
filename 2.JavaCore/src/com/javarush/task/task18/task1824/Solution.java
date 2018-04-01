package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/
import java.io.*;

public class  Solution {
    public static void main(String[] args) throws  IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName = "";
        FileInputStream f=null;

        while(true) {
            fileName = r.readLine();
            try {
                f = new FileInputStream(fileName);
                f.close();
            } catch (FileNotFoundException e) {
                System.out.println(fileName);
                break;
            }

        }
    }
}
