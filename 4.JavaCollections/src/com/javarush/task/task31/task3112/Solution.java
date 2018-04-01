package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path passwords = downloadFile("https://yastatic.net/morda-logo/i/citylogos/yandex19-logo-ru.png", Paths.get("c:/tmp"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }

    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException, URISyntaxException {
        // implement this method
        URL url=new URL(urlString);
        InputStream inputStream=url.openStream();

        Path tmp=Files.createTempFile("temp-",".tmp");
        Files.copy(inputStream,tmp);

        String fileName=urlString.substring(urlString.lastIndexOf("/"));
        Path destPath=Paths.get(downloadDirectory.toString()+ fileName);
        Files.move(tmp,destPath);

        return destPath;
    }
}
