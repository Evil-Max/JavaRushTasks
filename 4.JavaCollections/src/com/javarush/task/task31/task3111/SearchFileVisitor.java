package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    //partOfName, partOfContent, minSize, maxSize
    private String partOfName=null;
    private String partOfContent=null;
    private int minSize=-1;
    private int maxSize=-1;
    private List<Path> foundFiles = new ArrayList<Path>();

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        //byte[] content = Files.readAllBytes(file); // размер файла: content.length

        if (!attrs.isRegularFile()) return CONTINUE;
        if ((partOfName != null) && (!file.toString().contains(partOfName))) {
            return CONTINUE;
        }
        if ((minSize != -1)&&(attrs.size()<minSize)) {
            return CONTINUE;
        }
        if ((maxSize != -1) &&(attrs.size()>maxSize)) {
            return CONTINUE;
        }
        if ((partOfContent != null)&&(!(new String(Files.readAllBytes(file))).contains(partOfContent))) {
            return CONTINUE;
        };

        foundFiles.add(file);

        return CONTINUE;
    }
}
