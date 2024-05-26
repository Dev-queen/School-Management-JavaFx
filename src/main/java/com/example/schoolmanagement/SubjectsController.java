package com.example.schoolmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SubjectsController {

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

    }
}
