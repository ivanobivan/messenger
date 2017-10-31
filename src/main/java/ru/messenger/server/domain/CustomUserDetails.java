package ru.messenger.server.domain;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import ru.messenger.server.domain.User;

import java.util.Collection;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class CustomUserDetails implements UserDetails {

    @Autowired
    private MongoTemplate mongoTemplate;

    private String username;
    private String password;
    Collection<? extends GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Optional<User> findByUsername(String username) {
        Assert.assertNotNull(username);
        return Optional.ofNullable(
                mongoTemplate.findOne(Query.query(where(UserField.USER_NAME.getField()).is(username)), User.class));
    }

    public void save(User user) {
        Assert.assertNotNull(user);
        mongoTemplate.save(user);
    }
}

