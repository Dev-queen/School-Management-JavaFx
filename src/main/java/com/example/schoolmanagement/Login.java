package com.example.schoolmanagement;
import javafx.scene.control.Alert;
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

    private void showMessageDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Welcome Back!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void idPassCheck(ActionEvent e) {
        username = Id.getText();
        password = Password.getText();
        if (username.equals("Youmna") || username.equals("Mariam") && password.equals("123"))

        {
//           messageShown.setText("Welcome, " + username + " :)" );
            LogIn.setOnAction(event -> showMessageDialog("Welcome, " + username + " :)"));


        } else {
//            messageShown.setText("Username or password not recognized!");
            LogIn.setOnAction(event -> showMessageDialog("Username or Password not recognized!"));
            Id.clear();
            Password.clear();

        }
//        LogIn.setOnAction(event -> showMessageDialog("Welcome, " + username + " :)"));

    }
}

//Password
//Id

