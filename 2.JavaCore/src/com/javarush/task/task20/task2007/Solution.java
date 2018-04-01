package com.javarush.task.task20.task2007;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Как сериализовать JavaRush?
*/
public class Solution {
    public static class JavaRush implements Serializable {
        public List<User> users = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        /*
        File your_file_name = new File("c:/tmp/s.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream( new FileOutputStream(your_file_name));
        JavaRush javaRush = new JavaRush();
        User user = new User();
        user.setFirstName("Vasya");
        javaRush.users.add(user);
        outputStream.writeObject(javaRush);
        outputStream.close();
        */
    }
}
