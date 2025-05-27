package com.dsv.kafka.verification;

import com.dsv.kafka.model.Message;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MessageVerifierImpl implements MessageVerifier {
    
    private static final Logger logger = LoggerFactory.getLogger(MessageVerifierImpl.class);
    
    @Override
    public boolean verifyMessage(Message message, String headerTraceId) {
        if (message == null || headerTraceId == null) {
            logger.warn("Message or header traceId is null");
            return false;
        }
        
        boolean traceIdMatches = message.getTraceId().equals(headerTraceId);
        
        if (traceIdMatches) {
            logger.info("Message verification successful for traceId: {}", headerTraceId);
            return true;
        } else {
            logger.warn("TraceId mismatch. Expected: {}, Actual: {}", 
                       headerTraceId, message.getTraceId());
            return false;
        }
    }
}
