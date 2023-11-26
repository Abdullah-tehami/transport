package com.example.transportmanagementsystem;

public class Route {
    private String name;
    private final String destination = "Comsats LHR";
    private String pickupPoint;
    private Vehicle vehicle;

    public Route(String name, String pickupPoint, Vehicle vehicle){
        this.name = name;
        this.pickupPoint = pickupPoint;
        this.vehicle = vehicle;
    }
    @Override
    public String toString() {
        return name; // Display the name in the ComboBox
    }

    public Vehicle getVehicle(){
        return this.vehicle;
    }

    public String getPickupPoint(){
        return this.pickupPoint;
    }
    public String getDestination(){
        return this.destination;
    }

    public String getVehicleDetails() {
        // Customize the details format as needed
        return this.vehicle.getVehicleDetails();
    }
    public String toFileString() {
        // Format: name,pickupPoint,vehicleNumber
        return String.format("%s,%s,%s", name, pickupPoint, vehicle.getNumber());
    }

}
