package com.dsv.kafka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.qameta.allure.Step;
import java.time.LocalDateTime;

public class Message {
    @JsonProperty("id")
    private String id;

    @JsonProperty("content")
    private String content;

    @JsonProperty("traceId")
    private String traceId;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    public Message() {}

    public Message(String id, String content, String traceId) {
        this.id = id;
        this.content = content;
        this.traceId = traceId;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    @Step("Get message ID")
    public String getId() { return id; }
    
    @Step("Set message ID to: {id}")
    public void setId(String id) { this.id = id; }

    @Step("Get message content")
    public String getContent() { return content; }
    
    @Step("Set message content to: {content}")
    public void setContent(String content) { this.content = content; }

    @Step("Get trace ID")
    public String getTraceId() { return traceId; }
    
    @Step("Set trace ID to: {traceId}")
    public void setTraceId(String traceId) { this.traceId = traceId; }

    @Step("Get message timestamp")
    public LocalDateTime getTimestamp() { return timestamp; }
    
    @Step("Set message timestamp to: {timestamp}")
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    @Step("Convert message to string representation")
    @Override
    public String toString() {
        return "Message{id='" + id + "', content='" + content + "', traceId='" + traceId + "'}";
    }
}