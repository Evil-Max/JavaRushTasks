package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        String result=number+"=";
        int pow3=1;
        while(number>0) {
            int mod = number%3;
            number/=3;
            switch(mod) {
                case 1:
                    result+="+"+pow3;
                    break;
                case 2:
                    result+="-"+pow3;
                    number++;
                    break;
            }
            pow3*=3;
        }
        System.out.println(result);
    }
}