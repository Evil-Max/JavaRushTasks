package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Beast on 02.11.2017.
 */
public class CustomInvocationHandler implements InvocationHandler {
    SomeInterfaceWithMethods original;

    public CustomInvocationHandler(SomeInterfaceWithMethods si) {
        this.original = si;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object o;
        System.out.println(method.getName()+" in");
        o=method.invoke(original,args);
        System.out.println(method.getName()+" out");
        return o;
    }
}
