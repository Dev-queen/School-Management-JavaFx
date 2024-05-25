package com.example.schoolmanagement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


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

    public void idPassCheck(ActionEvent e) throws IOException {
        username = Id.getText();
        password = Password.getText();
        if ((username.equals("Youmna") || username.equals("Mariam")) && password.equals("123"))
        {
            showMessageDialog("Welcome, " + username + " :)");

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Stage stage = (Stage) LogIn.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        } else {
            showMessageDialog("Username or Password not recognized!");
            Id.clear();
            Password.clear();
        }

    }
}

