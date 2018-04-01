package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;

/* 
Закрепляем адаптер
*/

public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();

    static {
        countries.put("UA","Ukraine");
        countries.put("RU","Russia");
        countries.put("CA","Canada");
    }

    public static void main(String[] args) {
        /*
        DataAdapter da = new DataAdapter(
                new Customer() {
                    @Override
                    public String getCompanyName() {
                        return "JavaRush Ltd.";
                    }

                    @Override
                    public String getCountryName() {
                        return "Ukraine";
                    }
                },
                new Contact() {
                    @Override
                    public String getName() {
                        return "Ivanov, Ivan";
                    }

                    @Override
                    public String getPhoneNumber() {
                        return "+38(050)123-45-67";
                    }
                }
        );
        System.out.println(da.getCompany());
        System.out.println(da.getContactFirstName());
        System.out.println(da.getContactLastName());
        System.out.println(da.getDialString());
        System.out.println(da.getCountryCode());
        */
    }

    public static class DataAdapter implements RowItem {
        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.customer = customer;
            this.contact = contact;
        }

        public String getCountryCode() {
            String countryName = customer.getCountryName();

            for (Map.Entry<String, String> pair : countries.entrySet()) {
                if (countryName.equals(pair.getValue())) return pair.getKey();
            }
            return "";

        }    //example UA

        public String getCompany() {
            return customer.getCompanyName();
        }            //example JavaRush Ltd.
        public String getContactFirstName() {
            String name=contact.getName();
            return name.substring(name.indexOf(",")+2);
        }   //example Ivan
        public String getContactLastName() {
            String name=contact.getName();
            return name.substring(0,name.indexOf(","));
        }    //example Ivanov
        public String getDialString() {
            //+38(050)123-45-67
            String s = contact.getPhoneNumber();
            return "callto://"+s.replace("-","").replace("(","").replace(")","");
        }   //example callto://+380501234567

    }

    public static interface RowItem {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        String getDialString();         //example callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }
}