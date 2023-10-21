package ru.castroy10.kafkaspringboot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import ru.castroy10.kafkaspringboot.model.enumer.Topic;

public class RequestDto {
    @Valid
    @NotNull(message = "field have to be is not null")
    private String group;
    @NotNull(message = "field have to be is not null")
    private Topic topic;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
