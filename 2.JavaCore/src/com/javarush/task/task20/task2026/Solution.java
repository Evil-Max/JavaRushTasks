package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {

        byte[][] a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };

        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static void clearRect(int x,int y,byte[][] b) {
        for(int i=x;i<b.length;i++) {
            if (b[i][y]==0) break;
            for (int j = y; j < b.length; j++) {
                if (b[i][j]==0) break;
                b[i][j]=0;
            }
        }
    }

    public static int getRectangleCount(byte[][] a) {
        int result=0;
        byte[][] b=a.clone();

        for(int i=0;i<b.length;i++)
            for(int j=0;j<b.length;j++) {
                if (b[i][j] == 1) {
                    clearRect(i,j,b);
                    result++;
                }
            }
        return result;
    }
}
