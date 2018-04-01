package com.javarush.task.task18.task1815;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/* 
Таблица
*/

public class Solution {

    public class TableInterfaceWrapper implements ATableInterface {
        ATableInterface aT;

        public TableInterfaceWrapper(ATableInterface a) {
            aT = a;
        }

        @Override
        public void setModel(List rows) {
            System.out.println(rows.size());
            aT.setModel(rows);
        }

        @Override
        public String getHeaderText() {
            return aT.getHeaderText().toUpperCase();
        }

        @Override
        public void setHeaderText(String newHeaderText) {
            aT.setHeaderText(newHeaderText);
        }
    }

    public interface ATableInterface {
        void setModel(List rows);

        String getHeaderText();

        void setHeaderText(String newHeaderText);
    }

    public static void main(String[] args) {
    }
}