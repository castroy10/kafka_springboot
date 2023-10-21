package ru.castroy10.kafkaspringboot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import ru.castroy10.kafkaspringboot.dto.ModelDto;
import ru.castroy10.kafkaspringboot.dto.RequestDto;

import java.util.List;

public interface RestControllerService {
    ResponseEntity<?> send(ModelDto dto);

    List<ModelDto> get(RequestDto dto) throws JsonProcessingException;
}
