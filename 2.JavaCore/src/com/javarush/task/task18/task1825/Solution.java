package com.javarush.task.task18.task1825;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/* 
Собираем файл
*/
class FileNameComparator implements Comparator<String>{

    public int compare(String a, String b){
        Integer nA=Integer.parseInt(a.substring(a.indexOf(".part")+5));
        Integer nB=Integer.parseInt(b.substring(b.indexOf(".part")+5));

        return nA.compareTo(nB);
    }
}

public class Solution {

    public static void main(String[] args) throws IOException {
        TreeSet<String> parts = new TreeSet<String>(new FileNameComparator());
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        String fileResult = "";
        byte[] buffer = {};
        FileInputStream fIn=null;
        FileOutputStream fOut = null;
        //c:\tmp\d.txt.part1

        while(true) {
            s = r.readLine();
            if ("end".equals(s)) break;
            if ("".equals(fileResult)) {
                fileResult = s.substring(0,s.indexOf(".part"));
            }
            parts.add(s);
        }
        fOut = new FileOutputStream(fileResult,false);
        fOut.write(buffer);
        fOut.close();
        fOut = new FileOutputStream(fileResult,true);

        for(String fileName:parts) {
            fIn = new FileInputStream(fileName);
            if (fIn.available() >= 0) {
                buffer = new byte[fIn.available()];
                fIn.read(buffer);
                fOut.write(buffer);
            }
            fIn.close();
        }

        fOut.close();

    }


}
