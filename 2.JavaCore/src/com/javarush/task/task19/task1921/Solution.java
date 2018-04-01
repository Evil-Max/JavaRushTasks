package com.javarush.task.task19.task1921;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.*;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        //String fileName = "c:/tmp/z.txt";
        String s;
        String[] line;

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        SimpleDateFormat f = new SimpleDateFormat("dd MM yyyy");
        Date d;
        String name;
        while((s=br.readLine())!=null) {
            line = s.split(" ");
            if (line.length>3) {
                name = "";
                for (int i = 0; i < line.length - 3; i++) name += line[i] + " ";
                try {
                    d = f.parse(line[line.length - 3] + " " + line[line.length - 2] + " " + line[line.length - 1]);
                } catch (ParseException e) {
                    d = null;
                }
                PEOPLE.add(new Person(name.trim(), d));
            }
        }
        br.close();
        /*
        for(Person p:PEOPLE) {
            System.out.println(p.getName()+" "+p.getBirthday());
        }
        */
    }
}
