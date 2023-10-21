package ru.castroy10.kafkaspringboot.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Component;
import ru.castroy10.kafkaspringboot.model.LogEvent;
import ru.castroy10.kafkaspringboot.model.Message;
import ru.castroy10.kafkaspringboot.model.Model;
import ru.castroy10.kafkaspringboot.model.Payment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ConsumerReceiver {

    private final KafkaProperties kafkaProperties;
    private final ObjectMapper objectMapper;

    public ConsumerReceiver(KafkaProperties kafkaProperties, ObjectMapper objectMapper) {
        this.kafkaProperties = kafkaProperties;
        this.objectMapper = objectMapper;
    }

    public List<Model> pull(String group, String topic) throws JsonProcessingException {
        List<Model> result = new ArrayList<>();
        Map<String, Object> properties = kafkaProperties.buildConsumerProperties();
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, group);
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
//Если нужно получать все сообщения, конфигурием топик
        TopicPartition topicPartition = new TopicPartition(topic, 0);
        kafkaConsumer.assign(List.of(topicPartition));
        kafkaConsumer.seekToBeginning(List.of(topicPartition));
//если нужно будет получать не все сообщения, а только новые для группы консьюмеров
//        kafkaConsumer.subscribe(Collections.singleton(topic));
        ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(10));

        switch (topic) {
            case "MESSAGE":
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    Message message = objectMapper.readValue(consumerRecord.value(), Message.class);
                    result.add(message);
                }
                return result;
            case "PAYMENT":
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    Payment payment = objectMapper.readValue(consumerRecord.value(), Payment.class);
                    result.add(payment);
                }
                return result;
            case "LOG_EVENT":
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    LogEvent logEvent = objectMapper.readValue(consumerRecord.value(), LogEvent.class);
                    result.add(logEvent);
                }
                return result;
            default:
                return result;
        }
    }
}
