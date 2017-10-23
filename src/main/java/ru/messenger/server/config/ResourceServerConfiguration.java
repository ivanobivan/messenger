/*
package ru.messenger.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
<<<<<<< HEAD
                .antMatchers("/","/**","/js/**", "/pic/**", "/css/**","/index.html").permitAll()
=======
                .antMatchers("/","/login","/js/**", "/pic/**", "/css/**").permitAll()
>>>>>>> 5bfe7ffc0fe1d455c00b26c2d6637c10b60352ce
                .antMatchers("/chat.html").authenticated();
    }
}
*/
