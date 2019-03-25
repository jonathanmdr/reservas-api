package com.teste.reservasapi;

import com.teste.reservasapi.config.ReservasApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ReservasApiProperty.class)
public class ReservasApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservasApiApplication.class, args);
    }

}
