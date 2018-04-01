package com.javarush.task.task32.task3208;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/* 
RMI-2
*/
public class Solution {
    public static Registry registry;
    public static final  String UNIC_NAME_DOG = "dog.unic.name";
    public static final  String UNIC_NAME_CAT = "cat.unic.name";

    //pretend we start rmi client as CLIENT_THREAD thread
    public static Thread CLIENT_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (String bindingName : registry.list()) {
                    Animal service = (Animal) registry.lookup(bindingName);
                    service.printName();
                    service.say();
                }
            } catch (RemoteException e) {
                e.printStackTrace(System.err);
            } catch (NotBoundException e) {
                e.printStackTrace(System.err);
            }
        }
    });

    //pretend we start rmi server as SERVER_THREAD thread
    public static Thread SERVER_THREAD = new Thread(new Runnable() {

        @Override
        public void run() {
            //напишите тут ваш код
            try {

                final Animal dog = new Dog("Polkan");
                final Animal cat = new Cat("Pushok");

                registry = LocateRegistry.createRegistry(2099);

                Remote stub_dog = UnicastRemoteObject.exportObject(dog,0);
                Remote stub_cat = UnicastRemoteObject.exportObject(cat,0);

                registry.bind(UNIC_NAME_DOG, stub_dog);
                registry.bind(UNIC_NAME_CAT, stub_cat);
            } catch (RemoteException | AlreadyBoundException e) {
                e.printStackTrace(System.err);
            }
    }
    });

    public static void main(String[] args) throws InterruptedException {
        //start rmi server thread
        SERVER_THREAD.start();
        Thread.sleep(1000);
        //start client
        CLIENT_THREAD.start();
    }
}