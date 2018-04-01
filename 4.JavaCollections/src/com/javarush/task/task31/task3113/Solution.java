package com.javarush.task.task31.task3113;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
/*
Что внутри папки?
*/
public class Solution extends SimpleFileVisitor<Path> {
    int numFiles=0;
    int numDirs=0;
    long numBytes=0;

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        numDirs++;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        numBytes+=attrs.size();
        numFiles++;
        return FileVisitResult.CONTINUE;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String rootStr = r.readLine();
        Path root = Paths.get(rootStr);
        if (!Files.isDirectory(root)) {
            System.out.println(root.toAbsolutePath()+" - не папка");
            return;
        }
        Solution sol = new Solution();

        Files.walkFileTree(root, sol );
        System.out.println("Всего папок - "+(sol.numDirs-1));
        System.out.println("Всего файлов - "+sol.numFiles);
        System.out.println("Общий размер - "+sol.numBytes);
    }
}
