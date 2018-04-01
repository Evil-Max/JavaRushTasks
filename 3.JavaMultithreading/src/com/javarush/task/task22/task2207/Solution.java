package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        Map<String,String> map = new HashMap<>();

        StringBuilder sb = new StringBuilder();
        String fileName = (new BufferedReader(new InputStreamReader(System.in))).readLine();
        //String fileName = "c:/tmp/1.txt";
        //for(String s: Files.readAllLines(Paths.get(fileName), Charset.forName("Cp1251"))) sb.append(s+" ");
        for(String s: Files.readAllLines(Paths.get(fileName))) sb.append(s+" ");
        String ss[];

        for(String s:sb.toString().split("[\\s]")) {
            String reverse = new StringBuilder(s).reverse().toString();
            if (map.get(reverse)!=null) {
                map.put(reverse,s);
            } else if (map.get(s)==null) {
                map.put(s,"");
            }
        }
        for(Map.Entry<String,String> entry:map.entrySet()) {
            if (!entry.getValue().equals("")) {
                Pair pair = new Pair();
                pair.second=entry.getKey();
                pair.first=entry.getValue();
                result.add(pair);
            }
        }
        System.out.println(result.toString());
    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
            super();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
