package com.javarush.task.task24.task2406;

import java.math.BigDecimal;

/* 
Наследование от внутреннего класса
*/
public class Solution {
    public static class Building {
        public static class Hall {
            private BigDecimal square;

            public Hall(BigDecimal square) {
                this.square = square;
            }
        }

        public static class Apartments {
        }

    }

    public static class Apt3Bedroom extends Building.Apartments {

    }

    public static class BigHall extends Building.Hall {

        public BigHall(BigDecimal square) {
            super(square);
        }
    }

    public static void main(String[] args) {

    }
}
