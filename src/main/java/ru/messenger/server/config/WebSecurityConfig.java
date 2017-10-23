
package ru.messenger.server.config;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
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
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import ru.messenger.server.domain.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetails userDetails;


    @Qualifier("oauth2ClientContext")
    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .httpBasic()
                .and()
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
                .anyRequest().authenticated()
                .and()
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .exceptionHandling().accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> e.printStackTrace());

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
                Optional<User> tempUserObjectFromDB = userDetails.findByUsername(username);
                if (!tempUserObjectFromDB.isPresent()) {
                    //TODO banned option for create new logic for view banned user or now depending on some options
                    User newUser = new User(username, passwordEncoder().encode(password), AuthorityUtils.createAuthorityList(Role.ROLE_USER.getRole()));
                    userDetails.save(newUser);
                    return new UsernamePasswordAuthenticationToken(username, password, newUser.getAuthorities());
                } else if (tempUserObjectFromDB.get().isBanned()) {
                    return null;
                } else if (!passwordEncoder().matches(password, tempUserObjectFromDB.get().getPassword())) {
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
