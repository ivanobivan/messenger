package ru.messenger.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import ru.messenger.server.config.CustomUserDetails;
import ru.messenger.server.config.User;
import ru.messenger.server.config.UserDataService;
import ru.messenger.server.domain.Role;

import java.sql.Array;
import java.util.Arrays;


@SpringBootApplication
public class ApplicationSpring extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(ApplicationSpring.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationSpring.class, args);
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UserDataService service) throws Exception {
        builder.userDetailsService(s -> new CustomUserDetails(service.findByUsername(s)));
    }
}


