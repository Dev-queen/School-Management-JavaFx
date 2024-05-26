package com.example.schoolmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ExamsController {
    @FXML
    ImageView arrow;

    @FXML
    TableView<Marks> engTable;
    @FXML
    TableView<Marks> arabTable;
    @FXML
    TableView<Marks> sciTable;
    @FXML
    TableView<Marks> SSTable;
    @FXML
    TableView<Marks> mathTable;
    @FXML
    TableView<Marks> islamTable;

    @FXML
    TableColumn<Marks, String> colEngName;
    @FXML
    TableColumn<Marks, Float> colEngMarks;
    @FXML
    TableColumn<Marks, String> colArabName;
    @FXML
    TableColumn<Marks, Float> colArabMarks;
    @FXML
    TableColumn<Marks, String> colSciName;
    @FXML
    TableColumn<Marks, Float> colSciMarks;
    @FXML
    TableColumn<Marks, String> colSSName;
    @FXML
    TableColumn<Marks, Float> colSSMarks;
    @FXML
    TableColumn<Marks, String> colMathName;
    @FXML
    TableColumn<Marks, Float> colMathMarks;
    @FXML
    TableColumn<Marks, String> colIslamName;
    @FXML
    TableColumn<Marks, Float> colIslamMarks;

    ObservableList<Marks> engData = FXCollections.observableArrayList();
    ObservableList<Marks> arabData = FXCollections.observableArrayList();
    ObservableList<Marks> sciData = FXCollections.observableArrayList();
    ObservableList<Marks> SSData = FXCollections.observableArrayList();
    ObservableList<Marks> mathData = FXCollections.observableArrayList();
    ObservableList<Marks> islamData = FXCollections.observableArrayList();

    ArrayList<ObservableList<Marks>> observableLists = new ArrayList<>();
    ArrayList<TableView<Marks>> tables = new ArrayList<>();

    @FXML
    public void initialize() {
        observableLists.add(engData);
        observableLists.add(arabData);
        observableLists.add(sciData);
        observableLists.add(SSData);
        observableLists.add(mathData);
        observableLists.add(islamData);

        tables.add(engTable);
        tables.add(arabTable);
        tables.add(sciTable);
        tables.add(SSTable);
        tables.add(mathTable);
        tables.add(islamTable);

        setCellTable();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "SELECT name, courseName, marks FROM studentMarks";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            while (queryResult.next()) {
                String name = queryResult.getString("name");
                String courseName = queryResult.getString("courseName");
                float marks = queryResult.getFloat("marks");

                Marks marksObj = new Marks(name, marks);

                switch (courseName) {
                    case "English":
                        engData.add(marksObj);
                        break;
                    case "Arabic":
                        arabData.add(marksObj);
                        break;
                    case "Science":
                        sciData.add(marksObj);
                        break;
                    case "Social Studies":
                        SSData.add(marksObj);
                        break;
                    case "Math":
                        mathData.add(marksObj);
                        break;
                    case "Islamic":
                        islamData.add(marksObj);
                        break;
                }
            }
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }

        for (int i = 0; i < tables.size(); i++) {
            tables.get(i).setItems(observableLists.get(i));
        }
    }

    public void setCellTable() {
        colEngName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEngMarks.setCellValueFactory(new PropertyValueFactory<>("marks"));
        colArabName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colArabMarks.setCellValueFactory(new PropertyValueFactory<>("marks"));
        colSciName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSciMarks.setCellValueFactory(new PropertyValueFactory<>("marks"));
        colSSName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSSMarks.setCellValueFactory(new PropertyValueFactory<>("marks"));
        colMathName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMathMarks.setCellValueFactory(new PropertyValueFactory<>("marks"));
        colIslamName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colIslamMarks.setCellValueFactory(new PropertyValueFactory<>("marks"));
    }

    public void goToHome(MouseEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Stage stage = (Stage) arrow.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
