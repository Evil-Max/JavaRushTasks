package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber==null) return false;
        String regex = "(?=^\\D*(\\d\\D*){10}$|^\\+(\\d\\D*){12}$)^\\+?\\d*(\\(\\d{3}\\))?\\d*[-]?\\d+[-]?\\d+";
        return telNumber.matches(regex);
    }

    public static void main(String[] args) {
        System.out.println(checkTelNumber("(123)45-67-890"));
        System.out.println(checkTelNumber("1234567890"));
        System.out.println(checkTelNumber("+11(234)5678901"));
        /*
        String[] s = {
                "",
                "+380501234567",
                "+38(050)1234567",
                "+38050123-45-67",
                "050123-4567",
                "+38)050(1234567",
                "+38(050)1-23-45-6-7",
                "050ххх4567",
                "0501236",
                "(0)501234567",
                "+38(050)1-23-45--6-7",
                "+3-8(050)1-23-45--6-7",
                "+38050123-4567-"
        };
        for (String t : s) System.out.printf("%25s :   %5s %n",t,checkTelNumber(t));*/
    }
}
