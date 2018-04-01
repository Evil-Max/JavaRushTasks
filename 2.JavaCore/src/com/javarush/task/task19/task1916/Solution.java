package com.javarush.task.task19.task1916;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        List<String> lines1 = new ArrayList<String>();
        List<String> lines2 = new ArrayList<String>();

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = r.readLine();
        String fileName2 = r.readLine();
        //String fileName1 = "c:/tmp/1.txt";
        //String fileName2 = "c:/tmp/2.txt";
        r.close();

        String s;
        int o=0;
        BufferedReader br = new BufferedReader(new FileReader(fileName1));
        while((s=br.readLine())!=null) lines1.add(s);
        br.close();
        br = new BufferedReader(new FileReader(fileName2));
        while((s=br.readLine())!=null) lines2.add(s);
        br.close();

        String s1="",s2="",nextS1="",nextS2="";
        int ind1=0,ind2=0;

        while((ind1<lines1.size())|(ind2<lines2.size())) {

            if (ind1<(lines1.size())) s1=lines1.get(ind1); else s1="";
            if (ind2<(lines2.size())) s2=lines2.get(ind2); else s2="";

            if (ind1<(lines1.size()-1)) nextS1=lines1.get(ind1+1); else nextS1="";
            if (ind2<(lines2.size()-1)) nextS2=lines2.get(ind2+1); else nextS2="";

            if (s1.equals(s2)) {
                lines.add(new LineItem(Type.SAME,s1));
                ind1++;ind2++;
            } else {
                if ((s2.equals(nextS1))&(!s1.equals(nextS2))) { // равен следующей строке
                    lines.add(new LineItem(Type.REMOVED,s1));
                    ind1++;
                } else {
                    lines.add(new LineItem(Type.ADDED,s2));
                    ind2++;
                }
            }
        }
        for(LineItem i:lines) {
            System.out.println(i);
        }
    }




    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }

        @Override
        public String toString() {
            return type+" "+line;
        }
    }
}
