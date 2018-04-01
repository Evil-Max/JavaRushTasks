package com.javarush.task.task31.task3106;

import com.sun.nio.zipfs.ZipPath;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;


/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {


        String resultFileName = args[0];
        int filePartCount = args.length - 1;
        String[] fileNamePart = new String[filePartCount];
        for (int i = 0; i < filePartCount; i++) {
            fileNamePart[i] = args[i + 1];
        }
        Arrays.sort(fileNamePart);

        List<FileInputStream> fisList = new ArrayList<>();
        for (int i = 0; i < filePartCount; i++) {
            fisList.add(new FileInputStream(fileNamePart[i]));
        }
        SequenceInputStream seqInStream = new SequenceInputStream(Collections.enumeration(fisList));
        ZipInputStream zipInStream = new ZipInputStream(seqInStream);
        FileOutputStream fileOutStream = new FileOutputStream(resultFileName);
        byte[] buf = new byte[1024 * 1024];
        while (zipInStream.getNextEntry() != null) {
            int count;
            while ((count = zipInStream.read(buf)) != -1) {
                fileOutStream.write(buf, 0, count);
            }
        }
        seqInStream.close();
        zipInStream.close();
        fileOutStream.close();
    }

}
/*
class Sort implements Comparator<String> {
    public int compare(String a, String b) {
        int a1 = Integer.parseInt(a.substring(a.lastIndexOf(".") + 1));
        int b1 = Integer.parseInt(b.substring(b.lastIndexOf(".") + 1));
        return a.compareTo(b);
    }
}
*/