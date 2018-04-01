package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beast on 15.11.2017.
 */
public class Hippodrome {
    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void run() throws InterruptedException {
        for(int i=0;i<100;i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }
    public void move() {
        for(Horse horse:horses) {
            horse.move();
        }
    }
    public void print() {
        for(Horse horse:horses) {
            horse.print();
        }
        for(int i=0;i<10;i++)
            System.out.println();
    }
    public Horse getWinner() {
        double max=-1;
        Horse winner=null;
        for(Horse horse:horses) {
            if (max<horse.getDistance()) {
                max=horse.getDistance();
                winner=horse;
            }
        }
        return winner;
    }
    public void printWinner() {
        System.out.println("Winner is "+getWinner().getName()+"!");
    }

    private List<Horse> horses;
    public static Hippodrome game;

    public static void main(String[] args) throws InterruptedException {

        game = new Hippodrome(new ArrayList<Horse>());
        game.getHorses().add(new Horse("Horse#1",3,0));
        game.getHorses().add(new Horse("Horse#2",3,0));
        game.getHorses().add(new Horse("Horse#3",3,0));
        game.run();
        game.printWinner();
    }
}
