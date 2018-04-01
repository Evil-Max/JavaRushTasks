package com.javarush.task.task26.task2604;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/* 
Для того, чтобы усовершенствовать ум, надо больше размышлять, чем заучивать
*/
public class Solution extends Thread {
    public static final String DEFAULT_JAVARUSH_THREAD_NAME = "JavaRushThread";

    private static final AtomicInteger createdThreadIndex = new AtomicInteger();
    private static final AtomicInteger aliveThreadIndex = new AtomicInteger();

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
    }
    private static final Logger log = Logger.getLogger(Solution.class.getName());

    private static volatile boolean debugLifecycle = true;

    public Solution() {
        this(DEFAULT_JAVARUSH_THREAD_NAME);
    }

    public Solution(String name) {
        super(name + "-" + createdThreadIndex.incrementAndGet());

        setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                log.log(Level.SEVERE, "An error occurred in thread " + t.getName(), e);
            }
        });
    }

    public static void main(String[] args) {
        /*
        UnaryOperator<Integer> factorial = a->IntStream.range(1,a+1).reduce((x, y)->x*y).orElse(1);
        UnaryOperator<BigInteger> bigFactorial = a->Stream.iterate(BigInteger.valueOf(1),x->x.add(BigInteger.valueOf(1))).limit(a.longValue()).reduce((x,y)->x.multiply(y)).get();
        Function<Integer,BigInteger> bigNewFactorial = a->Stream.iterate(BigInteger.valueOf(1), x->x.add(BigInteger.valueOf(1))).limit(a).reduce((x, y)->x.multiply(y)).get();
        System.out.println(bigNewFactorial.apply(50));
        System.exit(0);
        */
        new Solution().start();
        new Solution().start();
        new Solution().start();
    }

    public void run() {
        // Copy debug flag to ensure consistent value throughout.
        boolean debug = debugLifecycle;
        if (debug) {
            log.log(Level.INFO, "Created " + getName());
        }
        try {
            aliveThreadIndex.incrementAndGet();
            log.log(Level.INFO, "Thread " + getName() + " in progress...");
            throw new RuntimeException("Oops " + getName());
        } finally {
            aliveThreadIndex.decrementAndGet();
            if (debug) {
                log.log(Level.INFO, "Exiting " + getName());
            }
        }
    }
}
