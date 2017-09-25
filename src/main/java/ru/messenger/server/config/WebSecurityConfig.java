package ru.messenger.server.config;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.messenger.server.domain.Role;

import java.util.List;
import java.util.Optional;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDataService userDataService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin()
                .loginPage("/index.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/chat.html")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/index.html")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/js/**", "/pic/**", "/css/**").permitAll()
                .anyRequest().authenticated();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //TODO need encrypt password in time authentication
        auth.authenticationProvider(new AuthenticationProvider() {
            @Override
            public boolean supports(Class<?> authentication) {
                return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
            }

            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
                String username = (String) token.getPrincipal();
                String password = (String) token.getCredentials();
                Assert.assertNotNull("Username is not defined", username);
                Assert.assertNotNull("Password is not defined", password);
                if (username.isEmpty() || password.isEmpty()) {
                    return null;
                }
                //TODO Add any tests for check invalid password and test for differ logIn and authorisation
                //TODO Need fix it error with NaN page
                Optional<User> tempUserObjectFromDB = userDataService.findByUsername(username);
                if(!tempUserObjectFromDB.isPresent()){
                    User newUser = new User();
                    newUser.setUsername(username);
                    newUser.setPassword(passwordEncoder().encode(password));
                    newUser.setAuthorities(AuthorityUtils.createAuthorityList(Role.ROLE_USER.getRole()));
                    newUser.setBanned(true);
                    //TODO banned option for create new logic for view banned user or now depending on some options
                    newUser.setBanned(false);
                    userDataService.save(newUser);
                    return new UsernamePasswordAuthenticationToken(username,password,newUser.getAuthorities());
                }else if(tempUserObjectFromDB.get().isBanned()){
                    return null;
                }else if(!passwordEncoder().matches(password,tempUserObjectFromDB.get().getPassword())){
                    return null;
                }
                //TODO create logic for admin/user/anonim in depending dome options
                List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(Role.ROLE_USER.getRole());
                return new UsernamePasswordAuthenticationToken(token.getName(), token.getCredentials(), authorities);
            }
        });

    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.debug(true);
    }


}
