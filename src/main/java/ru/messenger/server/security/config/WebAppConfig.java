package ru.messenger.server.security.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan("ru.messenger.server.security")
public class WebAppConfig extends WebMvcConfigurerAdapter {


    /*public static final String FONT = "classpath:static";

    @Autowired
    private ResourceLoader resourceLoader;
    Resource resource = resourceLoader.getResource(FONT);*/


    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/js/**").addResourceLocations("static/js/");
        registry.addResourceHandler("/js/**").addResourceLocations("resources/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("static/css/");
        registry.addResourceHandler("/pic/**").addResourceLocations("static/pic/");
    }*/

    /*@Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/resources/");
        resolver.setSuffix(".js");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }*/

}
