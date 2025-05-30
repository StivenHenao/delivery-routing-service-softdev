package com.softdev.delivery_routing.infrastructure.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softdev.delivery_routing.use_cases.service.ProcesarOrdenService;
import com.softdev.delivery_routing.use_cases.service.dtos.OrdenConDetallesDTO;

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
     * Método que escucha el mensaje de la cola de RabbitMQ y procesa la orden recibida.
     *
     * @param mensaje El mensaje recibido de la cola, que contiene los detalles de la orden.
     */
    @RabbitListener(queues = "purchase.order.queue")
    public void recibirOrden(final String mensaje) {
        try {
            System.out.println("Orden recibida: " + mensaje);
            
            // Deserializar el DTO de la orden
            OrdenConDetallesDTO ordenDto = objectMapper.readValue(mensaje, OrdenConDetallesDTO.class);
            
            // Procesar la orden
            procesarOrdenService.procesar(ordenDto);
            
        } catch (Exception e) {
            System.err.println("Error al procesar orden: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
