package ru.messenger.server.config;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import ru.messenger.server.domain.Role;
import ru.messenger.server.domain.UserField;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String username;
    private List<GrantedAuthority> authorities;
    private List<Role> roles;
    private String password;

    public User() {

    }

    public User(String username, String password,List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }


    public boolean isBanned() {
        return false;
    }
}
