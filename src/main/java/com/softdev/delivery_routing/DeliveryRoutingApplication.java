package com.softdev.delivery_routing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeliveryRoutingApplication {
    /**
     * Método principal que inicia el microservicio de ruteo de entregas.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(final String[] args) {
        SpringApplication.run(DeliveryRoutingApplication.class, args);
    }

}
