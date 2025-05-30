package com.softdev.delivery_routing.use_cases.service.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.Getter;

/**
 * Clase que representa una orden con detalles de un pedido.
 */
@Data
@Getter
public class OrdenConDetallesDTO {
    /**
     * Identificador único de la orden.
     */
    private String id;
    /**
     * Identificador de la orden asociada a la entrega.
     */
    private String emailCliente;
    /**
     * Nombre del cliente que realizó el pedido.
     */
    private String nombreCliente;
    /**
     * DNI del cliente que realizó el pedido.
     */
    private String dniCliente;
    /**
     * Detalles del pedido, como productos y cantidades.
     */
    private List<String> detalles;
    /**
     * Método de pago utilizado para el pedido.
     */
    private String metodoPago;
    /**
     * Valor total del pedido.
     */
    private BigDecimal valorTotal;
    /**
     * Fecha y hora en que se realizó el pedido.
     */
    private LocalDateTime fechaPedido;

    /**
     * Constructor por defecto.
     * Este constructor es necesario para la serialización y deserialización de objetos.
     */
    public OrdenConDetallesDTO() {
    }
}