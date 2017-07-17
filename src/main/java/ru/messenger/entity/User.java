package ru.messenger.entity;

import javax.persistence.*;

@Entity
@Table(name = "chat_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nickname;

    private String name;

    private String surname;

    private String mobileNumber;

    private String email;

    private byte[] avatar;

    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getAvatar() {
        return avatar;
    }
    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

}
