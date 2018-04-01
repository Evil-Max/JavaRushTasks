package com.javarush.task.task20.task2016;

import java.io.*;

/*
Минимум изменений
*/


public class Solution {
    public  class A implements Serializable {
        String name = "A";

        public A(String name) {
            this.name += name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public  class B extends A {
        String name = "B";

        public B(String name) {
            super(name);
            this.name += name;
        }
    }

    public  class C extends B {
        String name = "C";

        public C(String name) {
            super(name);
            this.name = name;
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*
        String fileName = "c:/tmp/data.txt";
        C classC = new C("Old");
        ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(fileName));
        ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(fileName));
        System.out.println(classC);
        oOut.writeObject(classC);
        C newC = new C("New");
        newC=(C)oIn.readObject();
        System.out.println(newC);

        oOut.close();
        oIn.close();
        */
    }
}
