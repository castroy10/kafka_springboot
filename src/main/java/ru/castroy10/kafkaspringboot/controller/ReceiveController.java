package ru.castroy10.kafkaspringboot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.castroy10.kafkaspringboot.dto.ModelDto;
import ru.castroy10.kafkaspringboot.dto.RequestDto;
import ru.castroy10.kafkaspringboot.service.RestControllerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/get")
public class ReceiveController {

    private final RestControllerService restControllerService;

    public ReceiveController(RestControllerService restControllerService) {
        this.restControllerService = restControllerService;
    }

    @PostMapping()
    public List<ModelDto> sendMessage(@Valid @RequestBody RequestDto dto) throws JsonProcessingException {
        return restControllerService.get(dto);
    }
}
