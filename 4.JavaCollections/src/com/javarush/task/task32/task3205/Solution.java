package com.javarush.task.task32.task3205;

import java.lang.reflect.Proxy;
import java.security.SecureClassLoader;

/*
Создание прокси-объекта
*/
public class Solution {
    public static void main(String[] args) {
        SomeInterfaceWithMethods obj = getProxy();
        obj.stringMethodWithoutArgs();
        obj.voidMethodWithIntArg(1);

        /* expected output
        stringMethodWithoutArgs in
        inside stringMethodWithoutArgs
        stringMethodWithoutArgs out
        voidMethodWithIntArg in
        inside voidMethodWithIntArg
        inside voidMethodWithoutArgs
        voidMethodWithIntArg out
        */
    }

    public static SomeInterfaceWithMethods getProxy() {
        SomeInterfaceWithMethods si = new SomeInterfaceWithMethodsImpl();
        ClassLoader loader = si.getClass().getClassLoader();
        Class<?>[] interfaces = si.getClass().getInterfaces();
        return (SomeInterfaceWithMethods) Proxy.newProxyInstance(loader,interfaces, new CustomInvocationHandler(si));
    }
}