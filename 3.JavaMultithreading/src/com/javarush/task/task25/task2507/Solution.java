package com.javarush.task.task25.task2507;

import java.io.*;
import java.net.*;

/* 
Работать в поте лица!
*/
public class Solution extends Thread {
    private static final int BUFFER_SIZE = 2000;    //2000 bytes
    private final Socket socket;
    private final InputStream in;

    public Solution(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    public void interrupt() {
        //implement logic here
        try {
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            super.interrupt();
        }

    }

    public void run() {
        StringBuilder sb = new StringBuilder(8096);
        try {
            byte[] buf = new byte[BUFFER_SIZE];
            while (true) {
                int count = in.read(buf);
                if (count < 0) {
                    break;
                } else {
                    if (count > 0) {
                        //process buffer here
                        sb.append((new String(buf,"UTF-8").toCharArray()));
                    }
                }
            }
            System.out.println(sb.toString());
        } catch (IOException ignored) {}
    }

    public static void main(String[] args) throws Exception     {
        InetAddress addr = InetAddress.getByName("localhost");
        Socket socket = new Socket(addr, 80);
        boolean autoflush = true;
        PrintWriter out = new PrintWriter(socket.getOutputStream(), autoflush);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // send an HTTP request to the web server
        out.println("GET http://localhost/dashboard/ HTTP/1.1");
        out.println("Host: http://localhost/dashboard/");
        out.println("Connection: Close");
        out.println();
        Solution solution = new Solution(socket);
        solution.start();
        Thread.sleep(1000);
        solution.interrupt();

        // read the response
        /*
        boolean loop = true;
        StringBuilder sb = new StringBuilder(8096);
        while (loop) {
            if (in.ready()) {
                int i = 0;
                while (i != -1) {
                    i = in.read();
                    sb.append((char) i);
                }
                loop = false;
            }
        }
        System.out.println(sb.toString());
        */
        //socket.close();
    }
}
