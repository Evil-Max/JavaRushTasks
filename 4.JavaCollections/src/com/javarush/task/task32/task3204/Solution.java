package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args)  {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream bt = new ByteArrayOutputStream();
        StringBuffer sb;
        Random randNumber = new Random();
        char[] listChar = new char[26+26+10];
        int i=0;
        char lt;
        boolean lL,lU,lD;

        for(char c='a';c<='z';c++) listChar[i++]=c;
        for(char c='A';c<='Z';c++) listChar[i++]=c;
        for(char c='0';c<='9';c++) listChar[i++]=c;

        while(true) {
            lL=lU=lD=false;
            sb = new StringBuffer();
            for (int j = 0; j < 8; j++) {
                lt = listChar[randNumber.nextInt(listChar.length)];
                sb.append(lt);
                lL = lL | Character.isLowerCase(lt);
                lU = lU | Character.isUpperCase(lt);
                lD = lD | Character.isDigit(lt);
            }
            if (lL&lU&lD) break;
        }
        try {
            bt.write(sb.toString().getBytes());
        } catch (Exception e) {};
        return bt;
    }
}