package com.example.transportmanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.application.Platform;
import java.net.URL;
import java.util.ResourceBundle;

public class BusPassController implements Initializable {
    private ComsatsBusManagement cbm;
    @FXML
    private TextArea busPass;
    @FXML
    private Button exitBtn;
    @FXML
    private Button logoutBtn;
    public void onExit() throws Exception{
        cbm.saveData();
        Platform.exit();
    }
    public void onLogout() throws  Exception{
        cbm.logout();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(new Scene(root, 691, 469));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        busPass.setText(cbm.printBusPass());
    }
}
