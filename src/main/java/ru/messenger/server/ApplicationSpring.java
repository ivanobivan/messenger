package ru.messenger.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import ru.messenger.database.manageDB.MongoDB;


@SpringBootApplication
public class ApplicationSpring extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(ApplicationSpring.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationSpring.class, args);
        MongoDB.getDB().drop();
    }

}