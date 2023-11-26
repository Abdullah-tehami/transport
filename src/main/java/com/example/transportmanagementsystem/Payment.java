package com.example.transportmanagementsystem;

import java.time.LocalDate;

public abstract  class Payment {
    private float amount;
    private LocalDate createdAt;
    public Payment(float amount, LocalDate createdAt) {
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public float getAmount() {
        return this.amount;
    }
    public LocalDate getCreatedAt() {
        return this.createdAt;
    }
    public abstract String printDetails();
}
