package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName = r.readLine(),s;
        r.close();
        //String fileName = "c:/tmp/1.html",s;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuffer sb = new StringBuffer();

        while((s=br.readLine())!=null) {
            sb.append(s);
        }
        br.close();
        /*
        args = new String[1];
        args[0]="span";
        */
        String tag = args[0];
        Pattern p = Pattern.compile("<"+tag +"[^>]*>|</"+tag +">");
        Matcher m = p.matcher(sb);

        ArrayList<Integer> start = new ArrayList<Integer>();
        Map<Integer,Integer> diap = new TreeMap <Integer,Integer>();

        while (m.find()) {
            if (!(m.group().substring(1,2).equals("/"))) { // тег открывается
                start.add(m.start());
            } else { // тег закрывается
                diap.put(start.get(start.size()-1),m.end());
                start.remove(start.size()-1);
            }
        }
        for(Map.Entry<Integer,Integer> d:diap.entrySet()) {
            System.out.println(sb.substring(d.getKey(),d.getValue()));
        }

    }
}
