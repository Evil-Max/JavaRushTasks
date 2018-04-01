package com.javarush.task.task31.task3107;

import java.nio.file.*;


/*
Null Object Pattern
*/
public class Solution {
    private FileData fileData;

    public Solution(String pathToFile) {
        Path file = Paths.get(pathToFile);

        try {
            fileData = new ConcreteFileData(
                    Files.isHidden(file),
                    Files.isExecutable(file),
                    Files.isDirectory(file),
                    Files.isWritable(file)
            );
        } catch (Exception e) {
            fileData = new NullFileData(e);
        }

    }

    public FileData getFileData() {
        return fileData;
    }
    public static void main(String[] args) {
        //Solution sol = new Solution("c:/tmp/1www.txt");
    }
}
