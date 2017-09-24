package ru.messenger.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String username;
    private List<GrantedAuthority> authorities;
    private String password;
    private boolean authorised;
    private boolean banned;

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthorised() {
        return authorised;
    }

    public void setAuthorised(boolean authorised) {
        this.authorised = authorised;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
