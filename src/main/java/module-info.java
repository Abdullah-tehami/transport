module com.example.transportmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.transportmanagementsystem to javafx.fxml;
    exports com.example.transportmanagementsystem;
}