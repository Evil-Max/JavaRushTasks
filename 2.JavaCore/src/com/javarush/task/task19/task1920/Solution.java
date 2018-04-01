package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.*;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        //String fileName = "c:/tmp/z.txt";
        String s;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        SortedMap<String,Double> salary = new TreeMap<String,Double>();
        String[] line;
        Double d;

        while((s=br.readLine())!=null) {
            line = s.split(" ");
            if (line.length==2) {
                d = salary.get(line[0]);
                if (d == null) d = 0.0;
                d += Double.parseDouble(line[1]);
                salary.put(line[0], d);
            }
        }
        br.close();
        d=0.0;
        for(Map.Entry<String,Double> p:salary.entrySet() )
            if (d<p.getValue()) d=p.getValue();

        for(Map.Entry<String,Double> p:salary.entrySet() ) {
            //System.out.println(p.getKey()+" "+p.getValue());
            if (p.getValue().equals(d)) {
                System.out.println(p.getKey());
            }
        }
    }
}
