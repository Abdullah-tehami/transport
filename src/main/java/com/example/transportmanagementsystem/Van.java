package com.example.transportmanagementsystem;

public class Van extends Vehicle{
    public Van(String number, int totalSeats, float charges){
        super(number, totalSeats, charges, "Van");
    }
    public void printVehicle(){
        System.out.println("Van Number: " + this.getNumber());
        super.printVehicle();
    }
}
