package ru.castroy10.kafkaspringboot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Value;
import ru.castroy10.kafkaspringboot.model.enumer.Status;

import java.time.LocalDateTime;

public class PaymentDto implements ModelDto {
    @Valid
    @Positive(message = "field have to be is > 0")
    private double amount;
    @NotNull (message = "field have to be is not null")
    private Status status;
    @NotNull (message = "field have to be is not null")
    private LocalDateTime timestamp;

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
