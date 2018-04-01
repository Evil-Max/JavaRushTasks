package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.*;


/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception {
        //implement this method - реализуйте этот метод
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = br.readLine();
        br.close();
        //String fileName = "c:/tmp/ini.ini";
        //String fileNameW = "c:/tmp/ini1.ini";
        FileInputStream f = new FileInputStream(fileName);
        //FileOutputStream w = new FileOutputStream(fileNameW);
        load(f);
        f.close();
        //save(w);
        //w.close();

    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties prop = new Properties();
        for(Map.Entry<String,String> entry:properties.entrySet()) {
            prop.setProperty(entry.getKey(),entry.getValue());
        }
        prop.store(outputStream,"");
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties prop = new Properties();
        prop.load(inputStream);
        Enumeration<?> e = prop.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = prop.getProperty(key);
            properties.put(key,value);
            //System.out.println("Key : " + key + ", Value : " + value);
        }
    }

    public static void main(String[] args) throws Exception {
        new Solution().fillInPropertiesMap();
    }
}
