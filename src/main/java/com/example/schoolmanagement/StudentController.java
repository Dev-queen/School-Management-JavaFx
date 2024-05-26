package com.example.schoolmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class StudentController {
    @FXML
    ImageView arrow ;

    @FXML
    TableView<Student> studentsTable;
    ObservableList<Student> studentsData;
    @FXML
    TableColumn<?,?> columnId;
    @FXML
    TableColumn<?,?> columnName;
    @FXML
    TableColumn<?,?> columnEmail;
    @FXML
    TableColumn<?,?> columnGrade;
    @FXML
    TableColumn<?,?> columnGender;

    @FXML
    TextField stdFN;
    @FXML
    TextField stdLN;
    @FXML
    TextField stdGrade;
    @FXML
    ChoiceBox genderChoiceBox;
    @FXML
    TextField stdEmail;

    public void setCellTable() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    @FXML
    public void initialize() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        studentsData = FXCollections.observableArrayList();
        setCellTable();

        String students = "SELECT * FROM students";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(students);
            while(queryResult.next()) {
                studentsData.add(new Student(queryResult.getInt(1), queryResult.getString(2), queryResult.getString(3), queryResult.getInt(4), queryResult.getString(5)));
            }
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        studentsTable.setItems(studentsData);
    }

    private void showMessageDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Done!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Add another student
    public void saveNewStudent(ActionEvent e) {

        String name = stdFN.getText() + " " + stdLN.getText();
        int grade = 0;
        String gender = "";
        if(!stdGrade.getText().isEmpty() && !genderChoiceBox.getValue().toString().isEmpty()) {
            grade = Integer.parseInt(stdGrade.getText());
            gender = genderChoiceBox.getValue().toString();
        }
        String email = stdEmail.getText();

        if (name.isEmpty() || grade == 0 || gender.isEmpty() || email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing data");
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        } else {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String newStudent = "INSERT INTO students(id, name, email, grade, gender) VALUES (?,?,?,?,?);";
            String lastStudent = "SELECT TOP 1 * FROM students ORDER BY id DESC;";


            try {
                Statement statement2 = connectDB.createStatement();
                ResultSet queryResult = statement2.executeQuery(lastStudent);
                PreparedStatement statement1 = connectDB.prepareStatement(newStudent);
                int id = 0;
                while(queryResult.next()) {id = queryResult.getInt(1) + 1;}

                statement1.setInt(1, id);
                statement1.setString(2, name);
                statement1.setString(3, email);
                statement1.setInt(4, grade);
                statement1.setString(5, gender);
                statement1.execute();

                showMessageDialog("Saved Successfully!");

                studentsData.clear();
                initialize();

            } catch (Exception error) {
                System.out.println(error.getMessage());
            }

        }
    }

    // Clear parameters
    public void clearTextFields(ActionEvent e) {
        stdFN.clear();
        stdLN.clear();
        stdGrade.clear();
        genderChoiceBox.setValue(null);
        stdEmail.clear();
    }

    // Delete a student
    public void deleteStudent(ActionEvent e) {
        String name = stdFN.getText() + " " + stdLN.getText();
        int grade = 0;
        String gender = "";
        if(!stdGrade.getText().isEmpty() && !genderChoiceBox.getValue().toString().isEmpty()) {
            grade = Integer.parseInt(stdGrade.getText());
            gender = genderChoiceBox.getValue().toString();
        }
        String email = stdEmail.getText();

        if (name.isEmpty() || grade == 0 || gender.isEmpty() || email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing data");
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        } else {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String newStudent = "DELETE FROM students WHERE email = ?;";


            try {
                PreparedStatement statement = connectDB.prepareStatement(newStudent);
                statement.setString(1, email);
                statement.execute();

                showMessageDialog("Deleted Successfully!");

                studentsData.clear();
                initialize();

            } catch (Exception error) {
                System.out.println(error.getMessage());
            }

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
