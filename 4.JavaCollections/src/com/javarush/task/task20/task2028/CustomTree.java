package com.javarush.task.task20.task2028;

import java.io.*;
import java.util.*;


/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList implements  Cloneable, Serializable  {
    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 150; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println(list.size());
        System.out.println("Expected 64, actual is " + ((CustomTree) list).getParent("129"));
        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
    }

    Entry<String> root=new Entry<>("0");

    static class Entry<T> implements  Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry (String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }
        void checkChildren() {
            if (leftChild!=null) availableToAddLeftChildren=false;
            if (rightChild!=null) availableToAddRightChildren=false;
        }
        boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }

    }

    String getParent(String s) {
        ArrayDeque<Entry> q = new ArrayDeque<>();
        Entry x=root;

        q.offer(x);
        while((x=q.poll())!=null) {
            if (!(x.elementName.equals(root.elementName))) {
                if (x.elementName.equals(s)) {
                    return x.parent.elementName;
                }
            }
            if (x.leftChild!=null) {
                q.offer(x.leftChild);
            }
            if (x.rightChild!=null) {
                q.offer(x.rightChild);
            }
        }
        return null;
    }

    @Override
    public boolean add(Object o) {
        String s = (String)o;
        Entry entry = new Entry(s);
        Entry x=root;
        ArrayDeque<Entry> q = new ArrayDeque<>();

        q.offer(x);
        while((x=q.poll())!=null) {
            if (x.isAvailableToAddChildren()) {
                entry.parent=x;
                if (x.availableToAddLeftChildren) {
                    x.leftChild = entry;
                    x.availableToAddLeftChildren=false;
                } else {
                    x.rightChild = entry;
                    x.availableToAddRightChildren=false;
                }
                return true;
            } else {
                q.offer(x.leftChild);
                q.offer(x.rightChild);
            }
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        ArrayDeque<Entry> q = new ArrayDeque<>();
        Entry x=root;

        q.offer(x);
        while((x=q.poll())!=null) {
            if (x.leftChild!=null) {
                if (x.leftChild.elementName.equals((String)o)) {
                    x.leftChild=null;
                    return true;
                }
                q.offer(x.leftChild);
            }
            if (x.rightChild!=null) {
                if (x.rightChild.elementName.equals((String)o)) {
                    x.rightChild=null;
                    return true;
                }
                q.offer(x.rightChild);
            }
        }
        return false;
    }


    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        ArrayDeque<Entry> q = new ArrayDeque<>();
        Entry x=root;
        int i=0;

        q.offer(x);
        while((x=q.poll())!=null) {
            i++;
            if (x.leftChild!=null) {
                q.offer(x.leftChild);
            }
            if (x.rightChild!=null) {
                q.offer(x.rightChild);
            }
        }
        return i-1;
    }
}
