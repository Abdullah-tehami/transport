package com.example.transportmanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private String number;
    private int totalSeats;
    private float charges;
    private String type;
    private List<Student> passenger;

    public Vehicle(String number, int totalSeats, float charges, String type){
        this.number = number;
        this.totalSeats = totalSeats;
        this.charges = charges;
        this.type = type;
        this.passenger = new ArrayList<>();                                              //
    }

    public String getNumber(){
        return this.number;
    }
    public int getTotalSeats(){
        return this.totalSeats;
    }
    public float getCharges(){
        return this.charges;
    }
    public float getDiscount(Student currentStudent){
        if(currentStudent.getType().equals(StudentType.Hostalite)){
            return 30;
        }
        return 0;
    }

    public float getTotalCharges(Student currentStudent){
        float discount = (this.getDiscount(currentStudent) / 100) * charges;
        float total = charges - discount;
        return total;
    }
    public String getPricingDetails(Student currentStudent){
        return "Charges: " + this.getCharges() +
                "\nDiscount: " + this.getDiscount(currentStudent) +"%" +
                "\nTotal: " + this.getTotalCharges(currentStudent);
    }

    public void addPassanger(Student passenger){
        this.passenger.add(passenger);
    }

    public void printVehicle(){
        System.out.println("Vehicle Number: " + this.number);
        System.out.println("Total Seats: " + this.totalSeats);
        System.out.println("Available Seats: " + this.getAvailableSeats());
    }
    public int getAvailableSeats(){
        return (this.totalSeats - this.passenger.size());
    }
    public String toString() {
        return number; // Display the name in the ComboBox
    }
    public String getVehicleDetails() {
        // Customize the details format as needed
        return "Number: " + this.getNumber() +
                "\nTotal Seats: " + this.getTotalSeats() +
                "\nAvailable Seats: " + this.getAvailableSeats() +
                "\nCharges: " + this.getCharges();
    }

    public String toFileString() {
        // Format: number,totalSeats,charges,passengerId1,passengerId2,...
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s,%d,%.2f,%s", number, totalSeats, charges, type));

        if (passenger != null && !passenger.isEmpty()) {
            stringBuilder.append(",");
            for (int i=0; i<passenger.size(); i++) {
                stringBuilder.append(String.format("%d,", passenger.get(i).getID()));
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1); // Remove the trailing comma
        }

        return stringBuilder.toString();
    }
}
