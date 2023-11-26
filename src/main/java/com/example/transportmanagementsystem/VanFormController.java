package com.example.transportmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class VanFormController implements Initializable {
    private ComsatsBusManagement cbm;
    private Vehicle selectedVan;

    @FXML
    private ComboBox<Vehicle> vanMenu;
    @FXML
    public TextField pickupPoint;

    @FXML
    public TextField destination;
    @FXML
    public TextArea vanDetails;
    @FXML
    public Button confirmVanBtn;


    public void selectedVan() throws Exception{
        selectedVan = vanMenu.getValue();
        vanDetails.setText(selectedVan.getVehicleDetails());
    }

    public void confirmVan() throws Exception{
        if(selectedVan != null && !pickupPoint.getText().isEmpty()) {
            String pickUpPoint = pickupPoint.getText();
            Route newRoute = cbm.addAndCreateVanRoute(pickUpPoint, selectedVan);
            cbm.confirmTransport(newRoute);
            Parent root = FXMLLoader.load(getClass().getResource("payment-details.fxml"));
            Stage window = (Stage) confirmVanBtn.getScene().getWindow();
            window.setScene(new Scene(root, 691, 469));
        }
        System.out.println("please select a van first!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Vehicle> routesList = FXCollections.observableArrayList(cbm.getVehicle("Van"));
        vanMenu.setItems(routesList);
        destination.setText("Comsats LHR");
    }
}
