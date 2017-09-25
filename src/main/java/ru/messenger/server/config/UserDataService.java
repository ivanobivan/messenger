package ru.messenger.server.config;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import ru.messenger.server.domain.UserField;

import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class UserDataService {

    @Autowired
    private MongoTemplate mongoTemplate;

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
