package com.springbootbackend.springbootbackend.Config;

import com.springbootbackend.springbootbackend.StudentDetails.StudentDetails;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

public class KafkaProducerConfig {
    @Value("${spring.kafka.topic.name}")
    private String kafkaService;

    public Map<String,Object> producerConfig(){
        HashMap<String,Object> config=new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaService);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return config;
    }

    @Bean
    public ProducerFactory<String,StudentDetails> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, StudentDetails> kafkaTemplate(ProducerFactory<String,StudentDetails> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}
