package com.beertag.beertagfinalproject.configurations;

import com.beertag.beertagfinalproject.models.*;
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
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Tag.class)
                .addAnnotatedClass(BeerTag.class)
                .addAnnotatedClass(Drink.class)
                .buildSessionFactory();
    }
}
