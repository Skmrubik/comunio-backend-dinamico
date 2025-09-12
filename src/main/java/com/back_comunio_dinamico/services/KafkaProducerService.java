package com.back_comunio_dinamico.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.back_comunio_dinamico.entities.MensajeJugadorDTO;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "my_topic";

    @Autowired
    private final KafkaTemplate<String, MensajeJugadorDTO> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, MensajeJugadorDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(MensajeJugadorDTO message) {
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Message sent: " + message);
    }
}