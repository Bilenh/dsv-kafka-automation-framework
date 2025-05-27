package com.dsv.kafka.consumer;

import com.dsv.kafka.model.Message;

public interface MessageConsumer {
    void startConsuming();
    void stopConsuming();
    Message receiveMessage(String topic);

}