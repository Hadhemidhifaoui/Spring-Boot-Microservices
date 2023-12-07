package com.example.springbootmicroservice3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class ChatMessage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private MessageType type;
    private String content;
    private String sender;
    private String rec;

    private Number dateMessege;




    public MessageType getType() {
        return type;
    }
    public void setType(MessageType type) {
        this.type = type;
    }

    public Number getDateMessege() {
        return dateMessege;
    }
    public void setDateMessege(Number dateMessege) {
        this.dateMessege = dateMessege;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRec() {
        return rec;
    }
    public void setRec(String rec) {
        this.rec = rec;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
