package ru.messenger.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chat_private_room")
public class PrivateRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstUser")
    private String firstUser;

    @Column(name = "secondUser")
    private String secondUser;

    @Column(name = "messages")
    private List<Message> messages;

    public PrivateRoom() {}

    public PrivateRoom(String firstUser, String secondUser, Message message) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
        List<Message> messages = new ArrayList<>();
        messages.add(message);
        this.messages = messages;
    }

    public Long getId() {
        return id;
    }

    public String getFirstUser() {
        return firstUser;
    }
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }

    public String getSecondUser() {
        return secondUser;
    }
    public void setSecondUser(String secondUser) {
        this.secondUser = secondUser;
    }

    public List<Message> getMessages() {
        return messages;
    }
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}
