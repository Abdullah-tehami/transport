package com.example.transportmanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;



public class TransportTypeController {
//    private ComsatsBusManagement cbm;

    @FXML
    public Button transportTypeBtn;
    @FXML
    public RadioButton busType;
    @FXML
    public RadioButton vanType;
//    public String selectedType;



    public void transportTypeSelected() throws Exception{
//        System.out.println(busType.isSelected());
//        System.out.println(vanType.isSelected());
        if(busType.isSelected()){
//            selectedType = "Bus";
            Parent root = FXMLLoader.load(getClass().getResource("bus-form.fxml"));
            Stage window = (Stage) transportTypeBtn.getScene().getWindow();
            window.setScene(new Scene(root, 691, 469));
        }
        else if(vanType.isSelected()){
//            selectedType = "Van";
            Parent root = FXMLLoader.load(getClass().getResource("van-form.fxml"));
            Stage window = (Stage) transportTypeBtn.getScene().getWindow();
            window.setScene(new Scene(root, 691, 469));
        }
        else{
            System.out.println("Please Select a Transport Type");
        }
//        System.out.println(selectedType);
    }
}