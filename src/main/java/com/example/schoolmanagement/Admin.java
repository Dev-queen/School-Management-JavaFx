package com.example.schoolmanagement;

public class Admin extends Employee{
    private final String username;
    private final String password;

    public Admin(String name, String email, String gender, float salary, String username, String password) {
        super(name, email, gender, salary);
        this.username = username;
        this.password = password;
    }

    public Admin(int id, String name, String email, String gender, float salary, String username, String password) {
        super(id, name, email, gender, salary);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
