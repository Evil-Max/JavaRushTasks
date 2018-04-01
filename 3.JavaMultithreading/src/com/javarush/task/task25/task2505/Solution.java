package com.javarush.task.task25.task2505;

/* 
Без дураков
*/


import java.util.logging.Level;
import java.util.logging.Logger;

public class Solution {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new Solution().new MyThread("super secret key");
        myThread.start();
    }

    public class MyThread extends Thread {
        private class MyUncaughtExceptionHandler implements UncaughtExceptionHandler {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                try {
                    Thread.sleep(500);
                    String s = String.format("%s, %s, %s",secretKey,t.getName(), e.getMessage());
                    System.out.println(s);
                    //Logger.getLogger(this.getClass().getName()).log(Level.WARNING,s);
                } catch (InterruptedException ex) {};

            }
        }
        private String secretKey;

        public MyThread(String secretKey) {
            this.secretKey = secretKey;
            setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            //setDaemon(true);
        }

        @Override
        public void run() {
            throw new NullPointerException("it's an example");
        }
    }

}

