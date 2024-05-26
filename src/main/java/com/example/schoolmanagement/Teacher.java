package com.example.schoolmanagement;

import java.util.Date;

public class Teacher extends Employee {
    private int courseId;
    private String courseName;
    private Course course;

    public Teacher(String name, String email, String gender, float salary, int courseId, String courseName) {
        super(name, email, gender, salary);
        this.courseId = courseId;
        this.courseName = courseName;
        this.course = new Course(courseId, courseName);
    }

    public Teacher(int id, String name, String email, String gender, float salary, int courseId, String courseName) {
        super(id, name, email, gender, salary);
        this.courseId = courseId;
        this.courseName = courseName;
        this.course = new Course(courseId, courseName);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
