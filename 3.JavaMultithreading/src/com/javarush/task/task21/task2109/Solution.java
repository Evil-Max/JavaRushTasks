package com.javarush.task.task21.task2109;

/* 
Запретить клонирование
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        public int hashCode() {
            int res = 31*i + j;
            return res;
        }

        @Override
        public boolean equals(Object obj) {
            A a = (A)obj;
            return (i==a.i)&&(j==a.j);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            A a = new A(this.i,this.j);
            return a;
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }
    }

    public static class C extends B implements Cloneable {
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        public int hashCode() {
            int res = this.getI();
            res = res*31 + this.getJ();
            res = res*31 + this.getName().hashCode();
            return res;
        }

        @Override
        public boolean equals(Object obj) {
            C c = (C)obj;
            return this.getI()==c.getI()&&this.getJ()==c.getJ()&&this.getName().equals(c.getName());
        }

        @Override
        protected C clone() throws CloneNotSupportedException {
            C c = new C(this.getI(),this.getJ(),this.getName());
            return c;
        }
    }

    public static void main(String[] args) {
        C c1 = new C(1,2,"name1");
        C c2 = null;
        try {
            c2 = c1.clone();
        } catch(CloneNotSupportedException e) {
            System.out.println(e);
        }
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c1.getI()+" : "+c1.getJ()+" - "+c1.getName());
        System.out.println(c2.getI()+" : "+c2.getJ()+" - "+c2.getName());
    }
}
