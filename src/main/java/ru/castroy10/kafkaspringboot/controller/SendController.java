package ru.castroy10.kafkaspringboot.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.castroy10.kafkaspringboot.dto.LogEventDto;
import ru.castroy10.kafkaspringboot.dto.MessageDto;
import ru.castroy10.kafkaspringboot.dto.PaymentDto;
import ru.castroy10.kafkaspringboot.service.RestControllerService;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
@RequestMapping("/api/v1/send")
public class SendController {

    public SendController(RestControllerService restControllerService) {
        this.restControllerService = restControllerService;
    }

    private final RestControllerService restControllerService;

    @PostMapping("msg")
    public ResponseEntity<?> sendMessage(@Valid @RequestBody MessageDto dto) {
        return restControllerService.send(dto);
    }

    @PostMapping("payment")
    public ResponseEntity<?> sendPayment(@Valid @RequestBody PaymentDto dto) {
        return restControllerService.send(dto);
    }

    @PostMapping("log")
    public ResponseEntity<?> sendLog(@Valid @RequestBody LogEventDto dto) {
        return restControllerService.send(dto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((e) -> {
            String fieldName = ((FieldError) e).getField();
            String message = e.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleValidation2(HttpMessageNotReadableException exception) {
        return ResponseEntity.badRequest().body(Map.of("Ошибка, значение находится вне допустимых вариантов значений", exception.getMessage()));
    }
}
