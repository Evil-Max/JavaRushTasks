package com.javarush.task.task31.task3101;

/* 
Проход по дереву файлов
*/
import java.io.*;
import java.util.*;
import java.text.*;

public class Solution {

    public static void nextDir(String path, ArrayList<File> list) {
        File file = null;

        for(String fileName: ((new File(path)).list())) {
            file = new File(path+"/"+fileName);
            if (file.isDirectory()) {
                nextDir(path+"/"+fileName,list);
            } else {
                if (file.length()>50) {
                    FileUtils.deleteFile(file);
                } else {
                    list.add(file);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        String path = args[0];
        String resultFileAbsolutePath = args[1];

        /*
		String path = "c:/tmp/test";
		String resultFileAbsolutePath = "c:/tmp/tmp.txt";
        */
        ArrayList<File> list = new ArrayList<File>();
        File file = new File(resultFileAbsolutePath);
        File fileD = new File(file.getParent()+"/allFilesContent.txt");
        FileUtils.renameFile(file,fileD);
        try (FileOutputStream outputStream = new FileOutputStream(fileD)) {

            nextDir(path, list);

            Collections.sort(list, new Comparator<File>() {
                        public int compare(File s1, File s2) {
                            return (s1.getName()).compareTo(s2.getName());
                        }
                    }
            );


            for (File f : list) {
                FileInputStream inputStream = new FileInputStream(f);
                if (inputStream.available() >= 0) {
                    byte[] buffer = new byte[inputStream.available()];
                    int count = inputStream.read(buffer);
                    outputStream.write(buffer, 0, count);
                    outputStream.write('\n');
                    outputStream.flush();
                }
                inputStream.close();
            }
            outputStream.close();
        }
    }

}
