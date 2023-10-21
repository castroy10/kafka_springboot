package ru.castroy10.kafkaspringboot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.castroy10.kafkaspringboot.dto.*;
import ru.castroy10.kafkaspringboot.kafka.ConsumerReceiver;
import ru.castroy10.kafkaspringboot.kafka.ProducerSender;
import ru.castroy10.kafkaspringboot.mapper.DataMapper;
import ru.castroy10.kafkaspringboot.model.LogEvent;
import ru.castroy10.kafkaspringboot.model.Message;
import ru.castroy10.kafkaspringboot.model.Model;
import ru.castroy10.kafkaspringboot.model.Payment;
import ru.castroy10.kafkaspringboot.model.enumer.Topic;

import java.util.List;

@Service
public class RestControllerServiceImpl implements RestControllerService {

    private final ProducerSender producerSender;
    private final DataMapper dataMapper;
    private final ConsumerReceiver consumerReceiver;

    public RestControllerServiceImpl(ProducerSender producerSender, DataMapper dataMapper, ConsumerReceiver consumerReceiver) {
        this.producerSender = producerSender;
        this.dataMapper = dataMapper;
        this.consumerReceiver = consumerReceiver;
    }

    @Override
    public ResponseEntity<?> send(ModelDto dto) {
        Model model = dataMapper.toEntity(dto);
        Topic topic = null;
        switch (dto.getClass().getSimpleName()) {
            case "MessageDto":
                topic = Topic.MESSAGE;
                break;
            case "PaymentDto":
                topic = Topic.PAYMENT;
                break;
            case "LogEventDto":
                topic = Topic.LOG_EVENT;
                break;
        }
        try {
            producerSender.send(topic, model);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @Override
    public List<ModelDto> get(RequestDto dto) throws JsonProcessingException {
        return consumerReceiver.pull(dto.getGroup(), dto.getTopic().toString()).stream().map(e -> dataMapper.toDto(e)).toList();
    }
}
