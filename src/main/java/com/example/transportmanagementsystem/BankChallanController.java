package com.example.transportmanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;


public class BankChallanController implements Initializable {
    private ComsatsBusManagement cbm;
    @FXML
    private TextArea bankChallan;
    @FXML
    private Button payNowBtn;
    public void payNow() throws Exception{
        cbm.generateBusPass();
        Parent root = FXMLLoader.load(getClass().getResource("bus-pass.fxml"));
        Stage window = (Stage) payNowBtn.getScene().getWindow();
        window.setScene(new Scene(root, 691, 469));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bankChallan.setText(cbm.printBankChallan());
    }
}
