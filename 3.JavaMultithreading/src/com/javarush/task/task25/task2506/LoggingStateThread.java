package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    Thread target;
    Thread.State state;

    public LoggingStateThread(Thread target) {
        //super(target);
        //this.setDaemon(true);
        this.target = target;
        state = target.getState();
        System.out.println(state);
    }

    @Override
    public void run() {

        while(state != State.TERMINATED) {
            Thread.State state_run=target.getState();
            if (state!=state_run) {
                System.out.println(state_run);
                state=state_run;
            }
        }
        this.interrupt();
    }
}
