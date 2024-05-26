package com.example.schoolmanagement;

import java.util.Date;

public class Teacher extends Employee {
    private Course course;

    public Teacher(String name, String email, String gender, double salary, Course course) {
        super(name, email, gender, salary);
        this.course = course;
    }

    public Teacher(int id, String name, String email, String gender, double salary, Date hireDate,Course course) {
        super(id, name, email, gender, salary);
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
