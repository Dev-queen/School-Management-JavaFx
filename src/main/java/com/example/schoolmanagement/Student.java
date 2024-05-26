package com.example.schoolmanagement;

import java.util.ArrayList;

public class Student extends Person{
    private int grade;
    private ArrayList<Course> courses;

    public Student(String name, String email, int grade, String gender) {
        super(name, email, gender);
        this.grade = grade;
    }

    public Student(int id, String name, String email, int grade, String gender) {
        super(id, name, email, gender);
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}
