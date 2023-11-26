package com.example.transportmanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class OnlinePaymentController implements Initializable {
    private ComsatsBusManagement cbm;
    @FXML
    private TextField accountNumber;
    @FXML
    private TextField bankPassword;
    @FXML
    private TextArea onlinePayment;
    @FXML
    private Button payNowBtn;
    @FXML
    private Label paymentStatusLabel;
    public void payNow() throws Exception{
        String accountNo = accountNumber.getText();
        String passWord = bankPassword.getText();
        boolean isPaid = cbm.payNow(accountNo, passWord);
        System.out.println(isPaid);
        if(isPaid) {
            Parent root = FXMLLoader.load(getClass().getResource("bus-pass.fxml"));
            Stage window = (Stage) payNowBtn.getScene().getWindow();
            window.setScene(new Scene(root, 691, 469));
        }
        paymentStatusLabel.setText("payment failed");

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onlinePayment.setText(cbm.printOnlinePaymentDetails());
    }
}
