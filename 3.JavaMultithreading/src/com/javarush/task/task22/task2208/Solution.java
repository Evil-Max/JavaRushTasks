package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        params.put("name","Ivanov");
        params.put("country","Ukraine");
        params.put("city","Kiev");
        params.put("age",null);
        System.out.println(getQuery(params));
    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,String> entry:params.entrySet()) {
            if (entry.getValue()!=null) {
                if (sb.length()>0) sb.append(" and ");
                sb.append(entry.getKey()+" = '"+entry.getValue()+"'");
            }
        }
        return sb.toString();
    }
}
