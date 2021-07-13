package com.notification.service.kafkaproducer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


public class Notification {
    private Long id;
    private String content;
    private NotificationType type;

     public Notification() {
     }

     public Notification(Long id, String content, NotificationType type) {
         this.id = id;
         this.content = content;
         this.type = type;
     }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public String getContent() {
         return content;
     }

     public void setContent(String content) {
         this.content = content;
     }

     public NotificationType getType() {
         return type;
     }

     public void setType(NotificationType type) {
         this.type = type;
     }

     @Override
     public String toString() {
         return "Notification{" +
                 "id=" + id +
                 ", content='" + content + '\'' +
                 ", type=" + type +
                 '}';
     }
 }
