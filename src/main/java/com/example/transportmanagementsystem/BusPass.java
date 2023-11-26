package com.example.transportmanagementsystem;

public class BusPass {
    private Student student;
    private Vehicle transport;
    public BusPass(Student student, Vehicle transport){
        this.student = student;
        this.transport = transport;
    }
    public String printDetails(Route selectedRoute){
        return "Student Details:\n" + this.student.getDetails() +
                "\n\nTransport Details:" +
                "\nNumber: " + this.transport.getNumber() +
                "\n\nRoute Details:" +
                "\nPickup Point: " + selectedRoute.getPickupPoint() +
                "\nDestination: " + selectedRoute.getDestination();
    }
}
