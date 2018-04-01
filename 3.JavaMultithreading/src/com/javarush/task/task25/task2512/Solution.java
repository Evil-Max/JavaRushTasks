package com.javarush.task.task25.task2512;

import java.util.ArrayDeque;
import java.util.Deque;

import static java.lang.Thread.currentThread;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Throwable next =e;
        t.interrupt();
        Deque d = new ArrayDeque();

        while(next!=null) {
            d.push(next);
            next=next.getCause();
        }
        while(d.peek()!=null) {
            next = (Throwable) d.pop();
            System.out.println(next);
        }
    }

    public static void main(String[] args)  {
        Solution solution = new Solution();
        solution.uncaughtException(new Thread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
        /*
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run()  {

                        try {
                            throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
                        } catch (Exception e) {

                        }

                    }
                }
        );
        thread.setUncaughtExceptionHandler(solution);
        thread.start();
*/
    }
}
