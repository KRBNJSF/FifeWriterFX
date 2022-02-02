module com.example.filefx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.filefx to javafx.fxml;
    exports com.example.filefx;
}