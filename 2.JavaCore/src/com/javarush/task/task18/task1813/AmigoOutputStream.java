package com.javarush.task.task18.task1813;

import java.io.*;

/* 
AmigoOutputStream
*/

public class AmigoOutputStream  extends FileOutputStream {
    public static String fileName = "C:/tmp/result.txt";
    FileOutputStream f;


    public AmigoOutputStream(FileOutputStream fo) throws FileNotFoundException {
        super(fileName);
        this.f = fo;
    }

    
    public void write(int b) throws IOException {
        f.write(b);
    }

    
    public void write(byte[] b) throws IOException {
        f.write(b);
    }

    
    public void write(byte[] b, int off, int len) throws IOException {
        f.write(b, off, len);
    }

    
    public void flush() throws IOException {
        f.flush();
    }

    
    public void close() throws IOException {
        f.flush();
        f.write("JavaRush © All rights reserved.".getBytes());
        f.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new AmigoOutputStream(new FileOutputStream(fileName));
    }

}
