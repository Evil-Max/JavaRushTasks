package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class University {

    private int age;
    private String name;
    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


    public University(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for(Student student:students) {
            if (student.getAverageGrade()==averageGrade) return student;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        double maxAverageGrade=Double.MIN_VALUE;
        Student studentWithMaxAverageGrade = null;
        for(Student student:students) {
            if (student.getAverageGrade()>maxAverageGrade) {
                studentWithMaxAverageGrade = student;
                maxAverageGrade = student.getAverageGrade();
            }
        }
        return studentWithMaxAverageGrade;
    }

    public Student getStudentWithMinAverageGrade() {
        double minAverageGrade=Double.MAX_VALUE;
        Student studentWithMinAverageGrade = null;
        for(Student student:students) {
            if (student.getAverageGrade()<minAverageGrade) {
                studentWithMinAverageGrade  = student;
                minAverageGrade = student.getAverageGrade();
            }
        }
        return studentWithMinAverageGrade;
    }

    public  void expel(Student student) {
        if (student != null && students.contains(student))
            students.remove(student);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}