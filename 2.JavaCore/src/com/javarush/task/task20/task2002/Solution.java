package com.javarush.task.task20.task2002;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            String your_file_name = "c:/tmp/d.txt";
            File file_name = new File(your_file_name);
            file_name.delete();

            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            User user = new User();
            user.setFirstName("Maxim");user.setLastName("Sergeev");user.setBirthDate(df.parse("01/10/1973"));user.setMale(true);user.setCountry(User.Country.RUSSIA);
            javaRush.users.add(user);
            user = new User();
            user.setFirstName("Vasya");user.setLastName("Pupkin");user.setBirthDate(df.parse("11/03/1999"));user.setMale(true);user.setCountry(User.Country.UKRAINE);
            javaRush.users.add(user);

            //javaRush.save(outputStream);
            //outputStream.flush();

            user = new User();
            user.setLastName("Smirnova");user.setCountry(User.Country.OTHER);
            // javaRush.users.add(user);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println(loadedObject.equals(javaRush));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            DataOutputStream outToFile = new DataOutputStream(outputStream);

            outToFile.writeInt(users.size());
            for (User user : users) {
                String firstName = (user.getFirstName() == null) ? "null" : user.getFirstName();
                outToFile.writeUTF(firstName);
                String lastName = (user.getLastName() == null) ? "null" : user.getLastName();
                outToFile.writeUTF(lastName);
                outToFile.writeLong(user.getBirthDate().getTime());
                outToFile.writeBoolean(user.isMale());
                outToFile.writeUTF(user.getCountry().name());
            }
            outToFile.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            DataInputStream fromFile = new DataInputStream(inputStream);

            int usersCount = fromFile.readInt();
            for (int i = 0; i < usersCount; i++) {
                User user = new User();

                String firstName = fromFile.readUTF();
                if (firstName.equals("null")) firstName = null;
                user.setFirstName(firstName);
                String lastName = fromFile.readUTF();
                if (lastName.equals("null")) lastName = null;
                user.setLastName(lastName);
                user.setBirthDate(new Date(fromFile.readLong()));
                user.setMale(fromFile.readBoolean());
                user.setCountry(User.Country.valueOf(fromFile.readUTF()));

                users.add(user);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
