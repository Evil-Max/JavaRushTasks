package com.javarush.task.task18.task1823;

import java.util.HashMap;
import java.util.Map;
import java.io.*;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        while(true) {
            s = r.readLine();
            if ("exit".equals(s)) break;
            resultMap.put(s,-1);
        }
        for(String fileName:resultMap.keySet()) {
            ReadThread rt = new ReadThread(fileName);
            rt.start();
        }

    }

    public static class ReadThread extends Thread {
        private String fileName;

        public ReadThread(String fileName) {
            //implement constructor body
            this.fileName = fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут
        public void run() {
            synchronized (this) {
                FileInputStream f = null;
                byte[] buffer = null;
                int[] cnt = new int[256];
                int max = 0, max_byte = -1;

                for (int i = 0; i < 256; i++) cnt[i] = 0;

                try {
                    f = new FileInputStream(fileName);
                    if (f.available() >= 0) {
                        buffer = new byte[f.available()];
                        f.read(buffer);
                    }
                    for (int i = 0; i < buffer.length; i++) {
                        cnt[buffer[i]]++;
                    }
                    f.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 256; i++) {
                    if (cnt[i] > max) {
                        max = cnt[i];
                        max_byte = i;
                    }
                }
                resultMap.put(fileName, max_byte);
            }
            return;
        }
    }
}
