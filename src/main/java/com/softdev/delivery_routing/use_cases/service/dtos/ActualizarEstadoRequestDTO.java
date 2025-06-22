package com.softdev.delivery_routing.use_cases.service.dtos;

import com.softdev.delivery_routing.domain.model.EstadoPedido;

import lombok.Data;
import lombok.Getter;

/**
 * DTO para actualizar el estado de un pedido.
 * Contiene el nuevo estado del pedido y el ID del repartidor asignado.
 */
@Data
@Getter
public class ActualizarEstadoRequestDTO {
    /**
     * Nuevo estado del pedido.
     */
    private EstadoPedido nuevoEstado;
    /**
     * Identificador del repartidor asignado a la entrega.
     */
    private String repartidorId;

    /**
     * Constructor por defecto.
     * Este constructor es necesario para la serialización y deserialización de objetos.
     */
    public ActualizarEstadoRequestDTO() {
    }
}
