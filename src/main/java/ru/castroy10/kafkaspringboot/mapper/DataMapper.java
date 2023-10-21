package ru.castroy10.kafkaspringboot.mapper;

import org.springframework.stereotype.Service;
import ru.castroy10.kafkaspringboot.dto.LogEventDto;
import ru.castroy10.kafkaspringboot.dto.MessageDto;
import ru.castroy10.kafkaspringboot.dto.ModelDto;
import ru.castroy10.kafkaspringboot.dto.PaymentDto;
import ru.castroy10.kafkaspringboot.model.LogEvent;
import ru.castroy10.kafkaspringboot.model.Message;
import ru.castroy10.kafkaspringboot.model.Model;
import ru.castroy10.kafkaspringboot.model.Payment;

@Service
public class DataMapper {

//    можно конечно было взять mapstruct или modelmapper.
    public Model toEntity(ModelDto dto) {
        switch (dto.getClass().getSimpleName()) {
            case "MessageDto":
                MessageDto messageDto = (MessageDto) dto;
                Message message = new Message();
                message.setAuthor(messageDto.getAuthor());
                message.setText(messageDto.getText());
                return message;
            case "PaymentDto":
                PaymentDto paymentDto = (PaymentDto) dto;
                Payment payment = new Payment();
                payment.setAmount(paymentDto.getAmount());
                payment.setStatus(paymentDto.getStatus());
                payment.setTimestamp(paymentDto.getTimestamp());
                return payment;
            case "LogEventDto":
                LogEventDto logEventDto = (LogEventDto) dto;
                LogEvent logEvent = new LogEvent();
                logEvent.setSystemName(logEventDto.getSystemName());
                logEvent.setText(logEventDto.getText());
                logEvent.setTimestamp(logEventDto.getTimestamp());
                return logEvent;
            default:
                return null;
        }
    }

    public ModelDto toDto(Model entity) {
        switch (entity.getClass().getSimpleName()) {
            case "Message":
                Message message = (Message) entity;
                MessageDto messageDto = new MessageDto();
                messageDto.setText(message.getText());
                messageDto.setAuthor(message.getAuthor());
                return messageDto;
            case "Payment":
                Payment payment = (Payment) entity;
                PaymentDto paymentDto = new PaymentDto();
                paymentDto.setAmount(payment.getAmount());
                paymentDto.setStatus(payment.getStatus());
                paymentDto.setTimestamp(payment.getTimestamp());
                return paymentDto;
            case "LogEvent":
                LogEvent logEvent = (LogEvent) entity;
                LogEventDto logEventDto = new LogEventDto();
                logEventDto.setSystemName(logEvent.getSystemName());
                logEventDto.setText(logEvent.getText());
                logEventDto.setTimestamp(logEvent.getTimestamp());
                return logEventDto;
            default:
                return null;
        }
    }
}
