package com.beertag.beertagfinalproject.configurations;

import com.beertag.beertagfinalproject.utils.Constants;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;

public class AppConfiguration {
    @Bean
    public SessionFactory provideSessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure(Constants.HIBERNATE_CONFIGURATION_FILE_NAME)
                .buildSessionFactory();
    }
}
