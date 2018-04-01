package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        for(Word w:detectAllWords(crossword, "home", "same")) {
            System.out.println(w);
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }


    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> result = new ArrayList<Word>();
        for(int i=0;i<crossword.length;i++) {
            for(int j=0;j<crossword[i].length;j++) {

                for(String w:words) {
                    if (w.charAt(0)==crossword[i][j]) {

                        for(int dx=-1;dx<=1;dx++)
                            for(int dy=-1;dy<=1;dy++) {
                                if ((dx!=0)|(dy!=0)) {
                                    int ii = i, jj = j;
                                    String buildWord="";
                                    while ((ii >= 0) & (ii < crossword.length) & (jj >= 0) & (jj < crossword[i].length)) {
                                        buildWord+=(char)crossword[ii][jj];
                                        if (buildWord.equals(w)) {
                                            Word newWord = new Word(w);
                                            newWord.setStartPoint(j,i);
                                            newWord.setEndPoint(jj,ii);
                                            result.add(newWord);
                                        }
                                        ii += dx;
                                        jj += dy;
                                    }
                                }
                            }

                    }
                }

            }
        }
        return result;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
