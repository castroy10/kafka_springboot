package ru.castroy10.kafkaspringboot.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Component;
import ru.castroy10.kafkaspringboot.config.KafkaConfig;

@Component
public class CustomConsumerFactory {
    private final KafkaConfig kafkaConfig;

    public CustomConsumerFactory(KafkaConfig kafkaConfig) {
        this.kafkaConfig = kafkaConfig;
    }

    @Bean("listenConsumerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConfig.consumerFactory());
        return factory;
    }

    @Bean
    public Consumer<String, Object> kafkaConsumer() {
        return kafkaConfig.consumerFactory().createConsumer();
    }
}
