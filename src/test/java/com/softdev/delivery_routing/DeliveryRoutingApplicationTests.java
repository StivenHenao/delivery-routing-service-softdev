package com.softdev.delivery_routing;

import com.softdev.delivery_routing.infrastructure.rest.EntregaController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test básico que verifica que el contexto de Spring Boot se carga correctamente
 * y que el controlador principal de entregas está presente.
 */
@SpringBootTest
class DeliveryRoutingApplicationTest {

    @Autowired
    private EntregaController entregaController;

    @Test
    void contextLoads() {
        // Verifica que el controlador fue inyectado por Spring correctamente
        assertThat(entregaController).isNotNull();
    }
}
