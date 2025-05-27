package com.dsv.kafka.producer;

import com.dsv.kafka.model.Message;

public interface MessageProducer {
    void sendMessage(String topic, Message message);

}