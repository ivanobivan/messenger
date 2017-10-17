package ru.messenger.server.config;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;


public interface UserDataService extends MongoRepository<User,Long>{

    User findByUsername(String username);

}
