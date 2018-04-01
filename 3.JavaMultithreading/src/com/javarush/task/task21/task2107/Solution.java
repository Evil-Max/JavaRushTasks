package com.javarush.task.task21.task2107;

import java.util.LinkedHashMap;
import java.util.Map;

/* 
Глубокое клонирование карты
*/
public class Solution implements Cloneable {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = (Solution) solution.clone();
            System.out.println(solution.toString());
            System.out.println(clone.toString());

            System.out.println(solution.users);
            System.out.println(clone.users);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    protected Map<String, User> users = new LinkedHashMap();

    @Override
    protected Solution clone() throws CloneNotSupportedException {
        Solution sol = new Solution();
        for(Map.Entry<String,User> e:users.entrySet()) {
            User user = (User)e.getValue().clone();
            sol.users.put(e.getKey(),user);
        }
        return sol;
    }


    public static class User implements Cloneable {
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int hashCode() {
            return age*31+name.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this==obj) return true;
            if (!(obj instanceof User)) return false;
            User u = (User)obj;
            if (this.age!=u.age) return false;
            return this.name.equals(u.name);
        }

        @Override
        protected User clone() throws CloneNotSupportedException {
            return new User(this.age,this.name);
        }

        @Override
        public String toString() {
            return name+":"+age;
        }
    }
}
