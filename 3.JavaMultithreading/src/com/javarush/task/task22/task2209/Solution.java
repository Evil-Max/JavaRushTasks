package com.javarush.task.task22.task2209;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;


/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //String fileName = "c:/tmp/1.txt";

        String fileName = (new BufferedReader(new InputStreamReader(System.in))).readLine();

        for(String s: Files.readAllLines(Paths.get(fileName)/*, Charset.forName("Cp1251")*/)) {
            StringBuilder result = getLine(s.split("[\\s]"));
            System.out.println(result.toString());
        }

        //String s = "Находка Киев Нью-Йорк Амстердам Вена Мельбурн Милан";
        //StringBuilder result = getLine();
        //System.out.println(result.toString());

    }

    public static StringBuilder getLine(String... words) {
        StringBuilder sb = new StringBuilder();
        if ((words!=null)&&(words.length>0)) {
            ArrayList<String> list = new ArrayList<>(Arrays.asList(words));
            sb.append(list.get(0));
            list.remove(0);
            while (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    String s = list.get(i);
                    char bg = Character.toLowerCase(sb.charAt(0));
                    char en = Character.toLowerCase(sb.charAt(sb.length() - 1));
                    if (Character.toLowerCase(s.charAt(0)) == en) {
                        sb.append(" " + s);
                        list.remove(i);
                    } else if (Character.toLowerCase(s.charAt(s.length() - 1)) == bg) {
                        sb.insert(0, s + " ");
                        list.remove(i);
                    }
                }
            }
        }
        return sb;
    }
}
