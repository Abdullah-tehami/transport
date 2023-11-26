package com.example.transportmanagementsystem;

import java.time.LocalDate;

public class OnlinePayment extends Payment{
    private long transactionId;

    public OnlinePayment(float amount, long transactionId, LocalDate createdAt) {
        super(amount, createdAt);
        this.transactionId = transactionId;
    }

    public long getTransactionId() {
        return transactionId;
    }
    public String printDetails() {
        return "Bank Name: Gibberish Bank Limited " +
                "\n\nTransaction Id: " +
                this.getTransactionId() +
                "\nCreated At: " +
                this.getCreatedAt() +
                "\nTotal Amount: " +
                this.getAmount();
    }
}
