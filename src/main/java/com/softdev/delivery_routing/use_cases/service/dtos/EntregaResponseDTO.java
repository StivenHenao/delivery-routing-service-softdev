package com.softdev.delivery_routing.use_cases.service.dtos;

import java.time.LocalDateTime;

import com.softdev.delivery_routing.domain.model.EstadoPedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * DTO para la respuesta de entrega de pedidos.
 * Contiene información sobre el pedido, cliente y repartidor.
 */
@Data
@Getter
@AllArgsConstructor
public class EntregaResponseDTO {
    /**
     * Identificador único de la entrega.
     */
    private String ordenId;
    /**
     * Nombre del cliente que realizó el pedido.
     */
    private String nombreCliente;
    /**
     * Email del cliente que realizó el pedido.
     */
    private String emailCliente;
    /**
     * DNI del cliente que realizó el pedido.
     */
    private String dniCliente;
    /**
     * Detalles del pedido, como productos y cantidades.
     */
    private EstadoPedido estado;
    /**
     * Método de pago utilizado para el pedido.
     */
    private String nombreRepartidor;
    /**
     * Valor total del pedido.
     */
    private String telefonoRepartidor;
    /**
     * Fecha y hora en que se realizó el pedido.
     */
    private LocalDateTime fechaUltimaActualizacion;

    /**
     * Constructor por defecto.
     * Este constructor es necesario para la serialización y deserialización de objetos.
     */
    public EntregaResponseDTO() {
    }
}