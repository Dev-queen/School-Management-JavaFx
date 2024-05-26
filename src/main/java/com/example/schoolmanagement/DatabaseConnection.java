package com.example.schoolmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public  Connection databaseLink;

    public Connection getConnection() {
        String dbName = "SchoolDB";
        String url = "jdbc:sqlserver://MARIAMALAA\\\\SQLEXPRESS:1433;databaseName=" + dbName + ";integratedSecurity=true;encrypt=false;trustServerCertificate=true";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            databaseLink = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            System.out.println("SQL Server JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed. Check output console.");
            e.printStackTrace();
        }

        return databaseLink;
    }
}