package com.softdev.delivery_routing.infrastructure.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
     * Tamaño máximo de memoria para el WebClient.
     * Se establece en 1 MB.
     */
    private static final int MAX_MEMORY_SIZE = 1024 * 1024; // 1 MB
    /**
     * Crea un bean de WebClient.Builder con configuración personalizada.
     * Establece el tamaño máximo de memoria y el tiempo de espera.
     *
     * @return WebClient.Builder configurado
     */
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(MAX_MEMORY_SIZE))
            .build()
            .mutate();
    }
}
