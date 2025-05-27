package com.dsv.kafka;

import com.dsv.kafka.service.AutomationFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaAutomationApp implements CommandLineRunner {
    
    @Autowired
    private AutomationFramework automationFramework;
    
    public static void main(String[] args) {
        SpringApplication.run(KafkaAutomationApp.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        automationFramework.runAutomation();
        
        // Keep running for demo
        Thread.sleep(10000);
        
        automationFramework.stopAutomation();
    }
}