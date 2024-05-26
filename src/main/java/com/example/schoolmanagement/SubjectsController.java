package com.example.schoolmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SubjectsController {

    @FXML
    ImageView arrow ;

    @FXML
    Label mathLabel;
    @FXML
    TextArea mathTextArea;

    @FXML
    Label englishLabel;
    @FXML
    TextArea englishTextArea;

    @FXML
    Label SSLabel;
    @FXML
    TextArea SSTextArea;

    @FXML
    Label sciLabel;
    @FXML
    TextArea sciTextArea;

    @FXML
    Label arabLabel;
    @FXML
    TextArea arabTextArea;

    @FXML
    Label islamicLabel;
    @FXML
    TextArea islamicTextArea;

    @FXML
    public void initialize() {

        ArrayList<Label> labels = new ArrayList<>();
        labels.add(mathLabel);
        labels.add(englishLabel);
        labels.add(SSLabel);
        labels.add(sciLabel);
        labels.add(arabLabel);
        labels.add(islamicLabel);

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String students = "SELECT * FROM teachersView";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(students);
            while(queryResult.next()) {
                String name = queryResult.getString(2);
                for(int i=0; i<6; i++) {
                    switch (queryResult.getString(7).toLowerCase()) {
                        case "math": mathTextArea.setText(name); break;
                        case "english": englishTextArea.setText(name); break;
                        case "social studies": SSTextArea.setText(name); break;
                        case "science": sciTextArea.setText(name); break;
                        case "arabic": arabTextArea.setText(name); break;
                        case "islamic": islamicTextArea.setText(name); break;
                    }
                }
            }
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }

    public void goToHome(MouseEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Stage stage = (Stage) arrow.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}