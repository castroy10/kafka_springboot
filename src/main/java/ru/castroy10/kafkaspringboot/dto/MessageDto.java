package ru.castroy10.kafkaspringboot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MessageDto implements ModelDto{
    @Valid
    @NotNull(message = "field have to be is not null")
    @NotBlank (message = "field have to be is not blank")
    private String text;
    @NotNull (message = "field have to be is not null")
    @NotBlank (message = "field have to be is not blank")
    private String author;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
