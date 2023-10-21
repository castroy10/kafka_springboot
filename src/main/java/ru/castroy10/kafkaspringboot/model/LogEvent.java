package ru.castroy10.kafkaspringboot.model;

import ru.castroy10.kafkaspringboot.model.enumer.SystemName;

import java.time.LocalDateTime;

public class LogEvent implements Model {
    private int id;
    private SystemName systemName;
    private String text;
    private LocalDateTime timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
