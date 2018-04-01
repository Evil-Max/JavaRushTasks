package com.javarush.task.task28.task2805;

public class MyThread extends Thread {
    private static int priorityCount = Thread.MIN_PRIORITY;

    static {
        if (priorityCount>=Thread.MAX_PRIORITY) priorityCount=Thread.MIN_PRIORITY;
    }
    private void incPriority() {
        if (priorityCount>Thread.MAX_PRIORITY) priorityCount=Thread.MIN_PRIORITY;
        if (this.getThreadGroup()!=null)
            this.setPriority (Math.min(priorityCount,this.getThreadGroup().getMaxPriority()));
        else
            this.setPriority(priorityCount);
        priorityCount++;
    }

    public MyThread() {
        super();
        incPriority();
    }

    public MyThread(Runnable target) {
        super(target);
        incPriority();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        incPriority();
    }

    public MyThread(String name) {
        super(name);
        incPriority();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        incPriority();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        incPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        incPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        incPriority();
    }
}
