package ru.castroy10.kafkaspringboot.model;

import ru.castroy10.kafkaspringboot.model.enumer.Status;

import java.time.LocalDateTime;

public class Payment implements Model {
    private int id;
    private double amount;
    private Status status;
    private LocalDateTime timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
