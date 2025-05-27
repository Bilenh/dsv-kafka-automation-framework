package com.dsv.kafka.verification;

import com.dsv.kafka.model.Message;

public interface MessageVerifier {
    boolean verifyMessage(Message message, String headerTraceId);
}