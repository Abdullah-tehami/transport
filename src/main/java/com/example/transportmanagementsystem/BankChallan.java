package com.example.transportmanagementsystem;

import java.time.LocalDate;

public class BankChallan extends Payment{
    private long challanNumber;
    private LocalDate expiryDate;

    public BankChallan(float amount, long challanNumber, LocalDate createdAt, LocalDate expiryDate) {
        super(amount, createdAt);
        this.challanNumber = challanNumber;
        this.expiryDate = expiryDate;
    }

    public long getChallanNumber() {
        return challanNumber;
    }
    public LocalDate getExpiryDate(){
        return this.expiryDate;
    }
    public String printDetails() {
        return "Bank Name: Gibberish Bank Limited " +
                "\n\nChallan No: " +
                this.getChallanNumber() +
                "\nCreated At: " +
                this.getCreatedAt() +
                "\nDue Date: " +
                this.getExpiryDate() +
                "\nTotal Amount: " +
                this.getAmount();
    }
}
