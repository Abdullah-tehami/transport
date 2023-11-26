package com.example.transportmanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentDetailsController implements Initializable{
    private ComsatsBusManagement cbm;
    @FXML
    private TextArea pricingDetails;
    @FXML
    private RadioButton onlinePayment;
    @FXML
    private RadioButton bankChallan;
    @FXML
    private Button confirmPaymentTypeBtn;

    public void paymentTypeSelected() throws Exception{
//      create payment object
        if(onlinePayment.isSelected()){
//            selectedPaymentType = "Bus";
            cbm.confirmPaymentType("OnlinePayment");
            Parent root = FXMLLoader.load(getClass().getResource("online-payment.fxml"));
            Stage window = (Stage) confirmPaymentTypeBtn.getScene().getWindow();
            window.setScene(new Scene(root, 691, 469));
        }
        else if(bankChallan.isSelected()){
//            selectedPaymentType = "Van";
            cbm.confirmPaymentType("BankChallan");
            Parent root = FXMLLoader.load(getClass().getResource("bank-challan.fxml"));
            Stage window = (Stage) confirmPaymentTypeBtn.getScene().getWindow();
            window.setScene(new Scene(root, 691, 469));
        }
        else{
            System.out.println("Please Select a Payment Type");
        }
//        System.out.println(selectedType);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pricingDetails.setText(cbm.printPaymentDetails());
    }
}
