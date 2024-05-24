package com.example.schoolmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {
    @FXML
    TextField Id;

    @FXML
    Label messageShown;

    @FXML
    Button LogIn ;

    @FXML
    PasswordField Password;
    String username;
    String password;


    public void idPassCheck(ActionEvent e) {
        username = Id.getText();
        password = Password.getText();
        if (username.equals("Youmna") || username.equals("Mariam") && password.equals("123")) {
            messageShown.setText("Welcome, " + username + " :)" );
        } else {
            messageShown.setText("Username or password not recognized!");
        }
    }
}

//Password
//Id