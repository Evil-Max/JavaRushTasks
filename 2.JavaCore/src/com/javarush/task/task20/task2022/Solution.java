package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        //out.close();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(this.fileName,true);
        //in.close();
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fileName = "c:/tmp/data.txt";
        FileOutputStream fOut = new FileOutputStream(fileName);
        ObjectOutputStream outputStream = new ObjectOutputStream(fOut);

        Solution solution = new Solution("c:/tmp/data1.txt");
        solution.writeObject("Test string");
        outputStream.writeObject(solution);

        fOut.close();
        outputStream.close();

        //loading
        FileInputStream fIn = new FileInputStream(fileName);
        ObjectInputStream objectStream = new ObjectInputStream(fIn);

        Solution loadedObject = (Solution) objectStream.readObject();
        loadedObject.writeObject("New test string");

        fIn.close();
        objectStream.close();
    }
}
