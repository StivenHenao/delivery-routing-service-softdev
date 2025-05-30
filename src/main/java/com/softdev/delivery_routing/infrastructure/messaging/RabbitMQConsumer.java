package com.softdev.delivery_routing.infrastructure.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softdev.delivery_routing.infrastructure.config.RabbitMQConfig;
import com.softdev.delivery_routing.use_cases.service.ProcesarOrdenService;
import com.softdev.delivery_routing.use_cases.service.dtos.OrdenConDetallesDTO;

@Component
public class RabbitMQConsumer {
    /**
     * Servicio para procesar órdenes.
     */
    private final ProcesarOrdenService procesarOrdenService;
    /**
     * Manejador de objetos para serialización/deserialización.
     */
    private final ObjectMapper objectMapper;

    /**
     * Constructor del consumidor de RabbitMQ.
     *
     * @param procesarOrdenServiceParam Servicio para procesar órdenes.
     * @param objectMapperParam Manejador de objetos para serialización/deserialización.
     */
    public RabbitMQConsumer(final ProcesarOrdenService procesarOrdenServiceParam, final ObjectMapper objectMapperParam) {
        this.procesarOrdenService = procesarOrdenServiceParam;
        this.objectMapper = objectMapperParam;
    }
    /**
     * Método que escucha la cola de RabbitMQ y procesa las órdenes recibidas.
     *
     * @param ordenDto DTO que contiene los detalles de la orden a procesar.
     */
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void recibirOrden(final OrdenConDetallesDTO ordenDto) {
        try {
            System.out.println("Orden recibida: " + ordenDto);

            // Procesar la orden
            procesarOrdenService.procesar(ordenDto);

        } catch (Exception e) {
            System.err.println("Error al procesar orden: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
