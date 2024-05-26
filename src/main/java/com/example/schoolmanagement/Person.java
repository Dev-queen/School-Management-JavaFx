package com.example.schoolmanagement;

public class Person implements SchoolIntity {
    private int id;
    private final String name;
    private String email;
    private final String gender;

    public Person(String name, String email, String gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public Person(int id, String name, String email, String gender) {
        this(name,email, gender);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
