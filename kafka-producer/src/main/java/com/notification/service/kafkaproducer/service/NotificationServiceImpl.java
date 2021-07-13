package com.notification.service.kafkaproducer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.service.kafkaproducer.exception.MapperException;
import com.notification.service.kafkaproducer.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private BrokerProducerService brokerProducerService;

    @Value("${producer.kafka.topic-name}")
    private String topicName;

    @Override
    public void send(Notification notification) {
        brokerProducerService.sendMessage(topicName, toJson(notification));
    }

    /**
     * Convert Object to json
     *
     * @param object object
     * @return string json
     */
    private <T> String toJson(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new MapperException(e.getMessage());
        }
    }
}
