package com.example.schoolmanagement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class HomePageController {

    @FXML
    ImageView studentsImageView;
    @FXML
    ImageView subjectsImageView;

    public void goToRegistration(MouseEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Student.fxml"));
        Stage stage = (Stage) studentsImageView.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void goToSubjects(MouseEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Subjects.fxml"));
        Stage stage = (Stage) subjectsImageView.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

}
