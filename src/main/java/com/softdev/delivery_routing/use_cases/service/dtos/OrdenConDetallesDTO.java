package com.softdev.delivery_routing.use_cases.service.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO que representa una orden con sus detalles.
 * Incluye información del cliente, detalles de la factura y el método de pago.
 */
@Data
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
     * Dirección de entrega del pedido.
     */
    private String direccion;
    /**
     * Detalles del pedido, como productos y cantidades.
     */
    private List<DetalleFacturaDTO> detalles;
    /**
     * Método de pago utilizado para el pedido.
     */
    private MetodoPagoFacturaDTO metodoPago;
    /**
     * Valor total del pedido.
     */
    private BigDecimal valorTotal;
    /**
     * Fecha y hora en que se realizó el pedido.
     */
    private LocalDateTime fechaPedido;

    /**
     * Estado actual de la entrega.
     */
    @Data
    public static class DetalleFacturaDTO {
        /**
         * Identificador único del detalle de la factura.
         */
        private String nombreProducto;
        /**
         * Cantidad del producto en el detalle de la factura.
         */
        private int cantidad;
        /**
         * Precio unitario del producto en el detalle de la factura.
         */
        private double precioUnitario;
        /**
         * Subtotal del detalle de la factura (cantidad * precio unitario).
         */
        private double subtotal;
    }

    /**
     * Método de pago utilizado para el pedido.
     */
    @Data
    public static class MetodoPagoFacturaDTO {
        /**
         * Identificador único del método de pago.
         */
        private String nombre;
    }
}
