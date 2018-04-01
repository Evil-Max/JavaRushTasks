package com.javarush.task.task31.task3102;

import java.io.*;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> result = new ArrayList<String>();
        List<File> listAll = new ArrayList<File>();

        int i=0;
        listAll.add(new File(root));

        while(i<listAll.size()) {
            File file = listAll.get(i++);
            if (file.isDirectory()) {
                listAll.addAll(Arrays.asList(file.listFiles()));
            } else {
                result.add(file.getAbsolutePath());
            }
        }
        return result;

    }

    public static void main(String[] args) throws IOException {
        List<String> result = getFileTree("C:/tmp");
        for(String s:result) System.out.println(s);
    }
}
