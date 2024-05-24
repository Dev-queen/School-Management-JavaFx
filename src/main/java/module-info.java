module com.example.schoolmanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.schoolmanagement to javafx.fxml;
    exports com.example.schoolmanagement;
}