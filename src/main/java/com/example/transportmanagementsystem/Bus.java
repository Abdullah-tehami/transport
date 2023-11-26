package com.example.transportmanagementsystem;

public class Bus extends Vehicle{
    public Bus(String number, int totalSeats, float charges){
        super(number, totalSeats, charges, "Bus");
    }
    public void printVehicle(){
        System.out.println("Bus Number: " + this.getNumber());
        super.printVehicle();                                                       //
    }
}
