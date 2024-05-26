package com.example.schoolmanagement;

import java.util.Date;

public class Employee extends Person {
    private float salary;
    private final Date hireDate;

    public Employee(String name, String email, String gender, float salary) {
        super(name, email, gender);
        this.salary = salary;
        this.hireDate = new Date();
    }

    public Employee(int id, String name, String email, String gender, float salary) {
        super(id, name, email, gender);
        this.salary = salary;
        this.hireDate = new Date();
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }
}
