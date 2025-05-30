package com.softdev.delivery_routing.infrastructure.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para RabbitMQ.
 * Configura la cola de pedidos de compra y el convertidor de mensajes JSON.
 */
@Configuration
public class RabbitMQConfig {

    /**
     * Crea una cola de pedidos de compra.
     * 
     * @return la cola configurada
     */
    @Bean
    public Queue purchaseOrderQueue() {
        return new Queue("purchase.order.queue", true);
    }
    
    /**
     * Crea un convertidor de mensajes JSON para RabbitMQ.
     * 
     * @return el convertidor de mensajes JSON configurado
     */
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Crea un contenedor de escucha para RabbitMQ.
     * Configura el convertidor de mensajes JSON y la conexión.
     * 
     * @param connectionFactory la fábrica de conexiones de RabbitMQ
     * @return el contenedor de escucha configurado
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(final ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }
}
