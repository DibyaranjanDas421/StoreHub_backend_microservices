package com.notification.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(NotificationServiceApplication.class, args);
  }

  
  @KafkaListener(topics="notificationtopic")
  public void handelNotification(OrderPlacedEvent orderPlacedEvent) {
	  
	  
	  log.info("Recived notification for Order -{}",orderPlacedEvent.getOrderNumber());
  }
}