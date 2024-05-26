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

public class TeachersController {
    @FXML
    ImageView arrow ;

    @FXML
    TableView<Teacher> teachersTable;
    ObservableList<Teacher> teachersData;
    @FXML
    TableColumn<?,?> columnId;
    @FXML
    TableColumn<?,?> columnName;
    @FXML
    TableColumn<?,?> columnEmail;
    @FXML
    TableColumn<?,?> columnGender;
    @FXML
    TableColumn<?,?> columnSalary;
    @FXML
    TableColumn<?,?> columnCrsId;
    @FXML
    TableColumn<?,?> columnCrsName;

    @FXML
    TextField tchFN;
    @FXML
    TextField tchLN;
    @FXML
    TextField tchSalary;
    @FXML
    TextField tchCrsId;
    @FXML
    ChoiceBox genderChoiceBox;
    @FXML
    TextField tchEmail;

    public void setCellTable() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        columnSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        columnCrsId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        columnCrsName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
    }

    @FXML
    public void initialize() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        teachersData = FXCollections.observableArrayList();
        setCellTable();

        String teachers = "SELECT * FROM teachersView";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(teachers);
            while(queryResult.next()) {
                teachersData.add(new Teacher(queryResult.getInt(1), queryResult.getString(2), queryResult.getString(3), queryResult.getString(4), queryResult.getFloat(5), queryResult.getInt(6), queryResult.getString(7)));
            }
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        teachersTable.setItems(teachersData);
    }

    private void showMessageDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Done!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Add another teacher
    public void saveNewTeacher(ActionEvent e) {

        String name = tchFN.getText() + " " + tchLN.getText();
        float salary = 0;
        int courseId = 0;
        String gender = "";
        if(!tchSalary.getText().isEmpty() && !genderChoiceBox.getValue().toString().isEmpty() && !tchCrsId.getText().isEmpty()) {
            salary = Float.parseFloat(tchSalary.getText());
            gender = genderChoiceBox.getValue().toString();
            courseId = Integer.parseInt(tchCrsId.getText());
        }
        String email = tchEmail.getText();

        if (name.isEmpty() || salary == 0 || gender.isEmpty() || courseId == 0 || email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing data");
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        } else {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String newStudent = "INSERT INTO teachers(id, name, email, gender, salary, courseId) VALUES (?,?,?,?,?,?);";
            String lastStudent = "SELECT TOP 1 * FROM teachersView ORDER BY id DESC;";


            try {
                Statement statement2 = connectDB.createStatement();
                ResultSet queryResult = statement2.executeQuery(lastStudent);
                PreparedStatement statement1 = connectDB.prepareStatement(newStudent);
                int id = 0;
                while(queryResult.next()) {id = queryResult.getInt(1) + 1;}

                statement1.setInt(1, id);
                statement1.setString(2, name);
                statement1.setString(3, email);
                statement1.setString(4, gender);
                statement1.setFloat(5, salary);
                statement1.setInt(6, courseId);
                statement1.execute();

                showMessageDialog("Saved Successfully!");

                teachersData.clear();
                initialize();

            } catch (Exception error) {
                System.out.println(error.getMessage());
            }

        }
    }

    // Clear parameters
    public void clearTextFields(ActionEvent e) {
        tchFN.clear();
        tchLN.clear();
        tchSalary.clear();
        genderChoiceBox.setValue(null);
        tchEmail.clear();
    }

    // Delete a teacher
    public void deleteTeacher(ActionEvent e) {
        String name = tchFN.getText() + " " + tchLN.getText();
        float salary = 0;
        int courseId = 0;
        String gender = "";
        if(!tchSalary.getText().isEmpty() && !genderChoiceBox.getValue().toString().isEmpty() && !tchCrsId.getText().isEmpty()) {
            salary = Float.parseFloat(tchSalary.getText());
            gender = genderChoiceBox.getValue().toString();
            courseId = Integer.parseInt(tchCrsId.getText());
        }
        String email = tchEmail.getText();

        if (name.isEmpty() || salary == 0 || gender.isEmpty() || courseId == 0 || email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing data");
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        } else {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String newTeacher = "DELETE FROM teachers WHERE email = ?;";


            try {
                PreparedStatement statement = connectDB.prepareStatement(newTeacher);
                statement.setString(1, email);
                statement.execute();

                showMessageDialog("Deleted Successfully!");

                teachersData.clear();
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
