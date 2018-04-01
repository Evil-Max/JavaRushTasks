package com.javarush.task.task20.task2017;

import java.io.*;

/* 
Десериализация
*/
public class Solution {
    public A getOriginalObject(ObjectInputStream objectStream) {

        Object o = null;
        try {
            o = objectStream.readObject();
        } catch (Exception e) {
            return null;
        }
        if (o instanceof  A) {
            return (A)o;
        }
        return null;
    }

    public class A implements Serializable {
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) throws IOException {
        /*
        String fileName = "c:/tmp/data.txt";
        B classB = new B();
        ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(fileName));
        ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(fileName));
        oOut.writeObject(classB);
        B newB = (B)getOriginalObject(oIn);

        oOut.close();
        oIn.close();
        */
    }
}
