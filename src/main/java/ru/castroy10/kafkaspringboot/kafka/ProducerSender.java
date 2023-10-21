package ru.castroy10.kafkaspringboot.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import ru.castroy10.kafkaspringboot.model.Model;
import ru.castroy10.kafkaspringboot.model.enumer.Topic;

import java.util.concurrent.CompletableFuture;

@Component
public class ProducerSender {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(ProducerSender.class);

    public ProducerSender(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<SendResult<String, Object>> send(Topic topic, Model model) {
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topic.toString(), "msg", model);
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(producerRecord);
        future.whenComplete((result, ex) -> {
            if (ex == null)
                log.info(("Запись успешна, offset " + result.getRecordMetadata().offset()) + " топик " + result.getRecordMetadata().topic());
            else log.info("Не удалось записать сообщение " + ex.getMessage());
        });
        return future;
    }
}