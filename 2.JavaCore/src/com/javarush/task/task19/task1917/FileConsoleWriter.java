package com.javarush.task.task19.task1917;

/* 
Свой FileWriter
*/

import java.io.*;

public class FileConsoleWriter {

    public static void main(String[] args) throws IOException {
        /*
        FileConsoleWriter f = new FileConsoleWriter("c:/tmp/fcw.txt");
        char[] c = {49,50,32,51,52,65};
        f.write(48);
        f.write(c);
        f.write(c,1,3);
        f.write("\r\nMaxim");
        f.write(" super-puper",6,6);
        f.close();
        */
    }

    private FileWriter fileWriter;

    public FileConsoleWriter(String fileName) throws IOException {
        fileWriter = new FileWriter(fileName);
    }

    public FileConsoleWriter(String fileName, boolean append) throws IOException {
        fileWriter = new FileWriter(fileName, append);
    }

    public FileConsoleWriter(File file) throws IOException {
        fileWriter = new FileWriter(file);
    }

    public FileConsoleWriter(File file, boolean append) throws IOException {
        fileWriter = new FileWriter(file, append);
    }

    public FileConsoleWriter(FileDescriptor fd) {
        fileWriter = new FileWriter(fd);
    }

    public void write(int c) throws IOException {
        fileWriter.write(c);
        System.out.print(c);
    }

    public void write(char[] cbuf) throws IOException {
        fileWriter.write(cbuf);
        System.out.print(cbuf);
    }

    public void write(char[] cbuf, int off, int len) throws IOException {
        fileWriter.write(cbuf, off, len);
        System.out.print(new String(cbuf,off,len));
        //System.out.print(Arrays.copyOfRange(cbuf,off,off+len));

    }

    public void write(String str) throws IOException {
        fileWriter.write(str);
        System.out.print(str);
    }

    public void write(String str, int off, int len) throws IOException {
        fileWriter.write(str, off, len);
        System.out.print(str.substring(off,off+len));
    }

    public void close() throws IOException {
        fileWriter.close();
    }
}
