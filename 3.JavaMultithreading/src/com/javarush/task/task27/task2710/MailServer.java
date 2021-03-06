package com.javarush.task.task27.task2710;

public class MailServer implements Runnable {
    private Mail mail;

    public MailServer(Mail mail) {
        this.mail = mail;
    }

    @Override
    public void run() {
        long beforeTime = System.currentTimeMillis();
        //сделайте что-то тут - do something here
        String text=null;
        while (text==null) {
            synchronized (mail) {
                text = mail.getText();
                if (text == null)
                    try {
                        mail.wait();
                    } catch (InterruptedException e) {

                    }
            }
        }
        String name = Thread.currentThread().getName();
        long afterTime = System.currentTimeMillis();
        synchronized(mail) {
            System.out.format("%s MailServer has got: [%s] in %d ms after start", name, mail.getText(), (afterTime - beforeTime));
        }
    }
}
