package ru.messenger.database.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Message")
@Table(name = "chat_message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "sender")
    private String sender;

    @Column(name = "recipient")
    private Long recipient;

    @Column(name = "date")
    private Date date;

    public Message() {}

    public Message(String text, String sender, Long recipient, Date date) {
        this.text = text;
        this.sender = sender;
        this.recipient = recipient;
        this.date = date;
    }

    public Long getId() {
        return id;
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
