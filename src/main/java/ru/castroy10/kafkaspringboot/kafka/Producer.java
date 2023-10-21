package ru.castroy10.kafkaspringboot.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.castroy10.kafkaspringboot.config.KafkaConfig;

@Component
public class Producer {

    private final KafkaConfig kafkaConfig;

    public Producer(KafkaConfig kafkaConfig) {
        this.kafkaConfig = kafkaConfig;
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        KafkaTemplate<String, Object> kafkaTemplate = new KafkaTemplate<>(kafkaConfig.producerFactory());
        kafkaTemplate.setConsumerFactory(kafkaConfig.consumerFactory());
        return kafkaTemplate;
    }
}
