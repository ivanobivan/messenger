package ru.messenger.database.entity;

import javax.persistence.*;
import java.util.TreeSet;

@Entity(name = "Room")
@Table(name = "chat_room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "admin")
    private String admin;

    @Column(name = "users")
    private TreeSet<String> users;

    public Room() {}

    public Room(String admin) {
        this.name = admin + "_room";
        this.admin = admin;
    }

    public Room(String name, String admin) {
        this.name = name;
        this.admin = admin;
        this.users = new TreeSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAdmin() {
        return admin;
    }
    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public TreeSet<String> getUsers() {
        return users;
    }
    public void setUsers(TreeSet<String> users) {
        this.users = users;
    }

}
