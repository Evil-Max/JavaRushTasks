package com.javarush.task.task20.task2025;

import java.util.ArrayList;

/*
Алгоритмы-числа
*/
public class Solution {
    public static long[][] makePowMatrix(int pow) {
        long[][] result = new long[8][pow-1];
        long sum;
        for(int i=2;i<10;i++)
            for(int j=2;j<=pow;j++) {
                sum=1;
                for(int k=0;k<j;k++) sum*=i;
                result[i-2][j-2]=sum;
            }
         return result;
    }

    public static  boolean checkNumber(long N,long[][] powMatrix) {
        long i=N,sum=0,sumN;
        byte digit,dNum=0;

        while(i>0) {
            i/=10;dNum++;
        }
        i=N;
        while(i>0) {
            digit = (byte)(i%10);
            i/=10;
            if (digit==1) {
                sum+=1;
            } else if (digit>0) {
                if (dNum == 1) sumN = digit;
                else sumN = powMatrix[digit - 2][dNum - 2];
                sum+=sumN;
            }
            if (sum>N) return false;
        }
        return (sum==N);
    };

    public static long[] getNumbers(long N) {
        long[] result = null;
        byte dNum=0;
        long k=N;
        while(k>0) {
            k/=10;dNum++;
        }

        long[][] powMatrix = makePowMatrix(dNum);
        ArrayList<Long> res = new ArrayList<Long>();
        for (long i=1;i<N;i++) {
            if (checkNumber(i,powMatrix)) {
                res.add(i);
            }
        }
        result=new long[res.size()];
        for(int i=0;i<res.size();i++) result[i]=res.get(i);
        return result;
    }

    public static void main(String[] args) {
        //long[][] powMatrix = makePowMatrix(19);
        //System.out.println(checkNumber(730,powMatrix ));
        /*
        long[] num =getNumbers(100000000);
        for (int i=0;i<num.length;i++) {
            System.out.println(num[i]);
        }
        */
    }
}
