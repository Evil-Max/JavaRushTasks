package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {

    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public boolean equals(Object m) {
        Solution n=(Solution)m;
        if (m==this) return true;
        if (m==null) return false;
        if (!(m instanceof  Solution)) return false;
        if (m.getClass()!=this.getClass()) return false;
        boolean r = ((n.first==null)?(first==null):(n.first.equals(first)))&&((n.last==null)?(last==null):(n.last.equals(last)));
        //System.out.println(r);
        return r;
        //return n.first.equals(first) && n.last.equals(last);
    }
    @Override
    public int hashCode() {
        //System.out.println(31 * ((first==null)?0:first.hashCode()) + ((last==null)?0:last.hashCode()));
        return 31 * ((first==null)?0:first.hashCode()) + ((last==null)?0:last.hashCode());
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<Solution>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}
