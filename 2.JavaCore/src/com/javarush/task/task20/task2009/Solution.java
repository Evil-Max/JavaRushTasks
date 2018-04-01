package com.javarush.task.task20.task2009;

import java.io.*;

/*
Как сериализовать static?
*/
public class Solution {
    public static class ClassWithStatic implements Serializable {
        public static String staticString = "it's test static string";
        public int i;
        public int j;
        public static void serializeStatic(ObjectOutputStream oos) throws IOException{
            oos.writeUTF(staticString);
        }
        public static void deserializeStatic(ObjectInputStream ois) throws IOException{
            staticString=ois.readUTF();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*
        File your_file_name = new File("c:/tmp/s.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream( new FileOutputStream(your_file_name));
        ClassWithStatic c = new ClassWithStatic();
        c.i=1;
        c.j=2;
        ClassWithStatic.serializeStatic(outputStream);
        outputStream.writeObject(c);
        outputStream.close();
        ClassWithStatic.staticString = "New String";

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(your_file_name));
        ClassWithStatic c1;
        ClassWithStatic.deserializeStatic(inputStream);
        c1=(ClassWithStatic) inputStream.readObject();
        c.i=3;
        c.j=4;
        inputStream.close();
        System.out.println(ClassWithStatic.staticString);
        System.out.println(c1.i);
        System.out.println(c1.j);
        */
    }
}
