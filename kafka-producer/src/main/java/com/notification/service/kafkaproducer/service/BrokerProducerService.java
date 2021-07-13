package com.notification.service.kafkaproducer.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * <h2>BrokerProducerService</h2>
 */
@Service
public class BrokerProducerService {

    private static final Logger logger = LoggerFactory.getLogger(BrokerProducerService.class);

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        // the KafkaTemplate provides asynchronous send methods returning a Future
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic, message);

        // you can register a callback with the listener to receive the result of the send asynchronously
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                logger.info("sent message='{}' with offset={}", message, result.getRecordMetadata().offset());
            }
            @Override
            public void onFailure(Throwable ex) {
                logger.error("unable to send message='{}'", message, ex);
            }
        });
    }
}
