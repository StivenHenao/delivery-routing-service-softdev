package com.softdev.delivery_routing.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter; // JSON converter
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para RabbitMQ.
 * Configura la cola de pedidos de compra y el convertidor de mensajes JSON.
 */
@Configuration
public class RabbitMQConfig {
    /**
     * Nombre del exchange de RabbitMQ.
     */
    public static final String EXCHANGE_NAME = "purchaseExchange";
    /**
     * Nombre de la cola de pedidos de compra.
     */
    public static final String QUEUE_NAME = "purchaseQueue";
    /**
     * Clave de enrutamiento para la cola de pedidos de compra.
     */
    public static final String ROUTING_KEY = "purchase.key";

    /**
     * Configura el exchange, la cola y el binding para RabbitMQ.
     *
     * @return Binding que conecta la cola al exchange con la clave de enrutamiento.
     */
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    /**
     * Configura la cola de pedidos de compra.
     *
     * @return Queue que representa la cola de pedidos de compra.
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }

    /**
     * Configura el binding entre la cola y el exchange con la clave de enrutamiento.
     *
     * @param queue La cola que se va a enlazar.
     * @param exchange El exchange al que se va a enlazar la cola.
     * @return Binding que conecta la cola al exchange con la clave de enrutamiento.
     */
    @Bean
    public Binding binding(final Queue queue, final DirectExchange exchange) {
        return BindingBuilder.bind(queue)
                             .to(exchange)
                             .with(ROUTING_KEY);
    }

    /**
     * Configura el convertidor de mensajes JSON para RabbitMQ.
     *
     * @return MessageConverter que utiliza Jackson para convertir mensajes a JSON.
     */
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Configura el RabbitTemplate para enviar mensajes a RabbitMQ.
     *
     * @param connectionFactory La conexión de RabbitMQ que se utilizará.
     * @return RabbitTemplate configurado con el convertidor de mensajes JSON.
     */
    @Bean
    public RabbitTemplate customRabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
