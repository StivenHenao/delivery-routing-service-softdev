package com.softdev.delivery_routing.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Clase de configuración para el cliente WebClient.
 * Configura el WebClient con un tamaño máximo de memoria y un tiempo de espera.
 */
@Configuration
public class WebClientConfig {

    /**
     * Crea un bean de WebClient.Builder con configuración personalizada.
     * Establece el tamaño máximo de memoria y el tiempo de espera.
     *
     * @return WebClient.Builder configurado
     */
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024))
            .build()
            .mutate();
    }
}
