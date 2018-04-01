package com.javarush.task.task31.task3109;

import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("c:/tmp/tmp.zip"));
        ZipEntry entry = new ZipEntry("777/");

        zip.putNextEntry(entry);
        zip.close();


        Solution solution = new Solution();

        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) throws IOException {
        Properties prop = new Properties();
        int x = fileName.lastIndexOf('.');
        String ext =  x>=0?fileName.substring(x):"";

        try {
            FileInputStream input = new FileInputStream(fileName);
            switch (ext) {
                case ".xml":
                    prop.loadFromXML(input);
                    break;
                default:
                    prop.load(input);
                    break;
            }

            input.close();
        } catch (Exception e) {
            //System.out.println(e);
        }

        return prop;
    }
}
