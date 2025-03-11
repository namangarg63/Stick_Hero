module com.example.ap_2022602_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ap_2022602_project to javafx.fxml;
    exports com.example.ap_2022602_project;
}