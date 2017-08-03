package ru.messenger.database.entity;

import java.util.Date;

public class Message {

    private String text;

    private String sender;

    private Long recipient;

    private Date date;

    public Message(String text, String sender, Long recipient, Date date) {
        this.text = text;
        this.sender = sender;
        this.recipient = recipient;
        this.date = date;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }

    public Long getRecipient() {
        return recipient;
    }
    public void setRecipient(Long recipient) {
        this.recipient = recipient;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}
