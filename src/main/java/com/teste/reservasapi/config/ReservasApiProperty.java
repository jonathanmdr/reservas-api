package com.teste.reservasapi.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties("reservas")
public class ReservasApiProperty {

    private String originPermitida = "http://localhost:8080";
}
