package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        T result = null;
        ObjectMapper mapper = new ObjectMapper();
        result = mapper.readValue(new FileReader(new File(fileName)), clazz);
        return result;
    }

    public static void main(String[] args) throws IOException {
        /*Cat cat = convertFromJsonToNormal("c:/tmp/js.txt",Cat.class);
        System.out.println(cat.name);
        System.out.println(cat.age);
        System.out.println(cat.weight);*/
    }
/*
    @JsonAutoDetect
    public static class Cat {
        public String name;
        public int age;
        public int weight;
        Cat() {
        }
    }*/
}
