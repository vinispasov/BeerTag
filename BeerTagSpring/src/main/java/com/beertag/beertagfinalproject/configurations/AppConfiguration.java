package com.beertag.beertagfinalproject.configurations;

import com.beertag.beertagfinalproject.models.Beer;
import com.beertag.beertagfinalproject.models.Rating;
import com.beertag.beertagfinalproject.models.User;
import com.beertag.beertagfinalproject.utils.Constants;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public SessionFactory provideSessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure(Constants.HIBERNATE_CONFIGURATION_FILE_NAME)
                .addAnnotatedClass(Beer.class)
                .addAnnotatedClass(Rating.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
}
