package ru.castroy10.kafkaspringboot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.castroy10.kafkaspringboot.model.enumer.SystemName;

import java.time.LocalDateTime;

public class LogEventDto implements ModelDto {
    @Valid
    private SystemName systemName;
    @NotNull (message = "field have to be is not null")
    @NotBlank (message = "field have to be is not blank")
    private String text;
    @NotNull
    private LocalDateTime timestamp;

    public SystemName getSystemName() {
        return systemName;
    }

    public void setSystemName(SystemName systemName) {
        this.systemName = systemName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
