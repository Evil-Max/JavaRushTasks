package com.javarush.task.task27.task2710;

public class Mail {
    private volatile String text;

    synchronized public String getText() {
        return text;
    }

    synchronized public void setText(String text) {
        this.text = text;
    }
}
