package com.example.transportmanagementsystem;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class BusFormController implements Initializable {
    private ComsatsBusManagement cbm;
    private Route selectedRoute;
    @FXML
    public ComboBox<Route> busRouteMenu;
    @FXML
    public TextField pickupPoint;

    @FXML
    public TextField destination;
    @FXML
    public TextArea busDetails;
    @FXML
    public Button confirmBusBtn;


    public void selectedRoute() throws Exception{
        selectedRoute = busRouteMenu.getValue();
        pickupPoint.setText(selectedRoute.getPickupPoint());
        destination.setText(selectedRoute.getDestination());
        busDetails.setText(selectedRoute.getVehicleDetails());
    }

    public void confirmBus() throws Exception{
        if(selectedRoute != null) {
            cbm.confirmTransport(selectedRoute);
            Parent root = FXMLLoader.load(getClass().getResource("payment-details.fxml"));
            Stage window = (Stage) confirmBusBtn.getScene().getWindow();
            window.setScene(new Scene(root, 691, 469));
        }
        System.out.println("please select route/bus");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Route> routesList = FXCollections.observableArrayList(cbm.getRoutes("Bus"));
        busRouteMenu.setItems(routesList);
    }
}
