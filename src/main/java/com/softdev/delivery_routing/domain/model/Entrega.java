package com.softdev.delivery_routing.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.softdev.delivery_routing.use_cases.service.dtos.OrdenConDetallesDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa una entrega de un pedido.
 */
@Data
@Getter
@Setter
public class Entrega {
    /**
     * Identificador único de la entrega.
     */
    private String id;
    /**
     * Identificador de la orden asociada a la entrega.
     */
    private String ordenId;
    /**
     * Email del cliente que realizó el pedido.
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
     * Estado actual de la entrega.
     */
    private EstadoPedido estado;
    /**
     * Identificador del repartidor asignado a la entrega.
     */
    private String repartidorId;
    /**
     * Nombre del repartidor asignado a la entrega.
     */
    private String nombreRepartidor;
    /**
     * Teléfono del repartidor asignado a la entrega.
     */
    private String telefonoRepartidor;
    /**
     * Fecha y hora en que se asignó el repartidor a la entrega.
     */
    private LocalDateTime fechaAsignacion;
    /**
     * Fecha y hora de la última actualización del estado de la entrega.
     */
    private LocalDateTime fechaUltimaActualizacion;

    /**
     * Constructor por defecto que inicializa el estado de la entrega a CREADA
     * y la fecha de última actualización a la fecha y hora actual.
     */
    public Entrega() {
        this.estado = EstadoPedido.CREADA;
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }

    /**
     * Constructor que inicializa una entrega con los detalles de la orden.
     *
     * @param ordenIdParam Identificador de la orden asociada a la entrega.
     * @param emailClienteParam Email del cliente que realizó el pedido.
     * @param nombreClienteParam Nombre del cliente que realizó el pedido.
     * @param dniClienteParam DNI del cliente que realizó el pedido.
     * @param direccionParam Dirección de entrega del pedido.
     * @param detallesParam Detalles del pedido, como productos y cantidades.
     * @param metodoPagoParam Método de pago utilizado para el pedido.
     * @param valorTotalParam Valor total del pedido.
     * @param fechaPedidoParam Fecha y hora en que se realizó el pedido.
     */
    public Entrega(final String ordenIdParam, final String emailClienteParam, final String nombreClienteParam,
                final String dniClienteParam, final String direccionParam, final List<OrdenConDetallesDTO.DetalleFacturaDTO> detallesParam,
                final OrdenConDetallesDTO.MetodoPagoFacturaDTO metodoPagoParam,
                final BigDecimal valorTotalParam, final LocalDateTime fechaPedidoParam) {
        this();
        this.ordenId = ordenIdParam;
        this.emailCliente = emailClienteParam;
        this.nombreCliente = nombreClienteParam;
        this.dniCliente = dniClienteParam;
        this.direccion = direccionParam;
        this.detalles = detallesParam.stream()
            .map(detalle -> detalle.getNombreProducto() + " (x" + detalle.getCantidad() + ")")
            .collect(Collectors.toList());
        this.metodoPago = metodoPagoParam.getNombre();
        this.valorTotal = valorTotalParam;
        this.fechaPedido = fechaPedidoParam;
    }


    /**
     * Asigna un repartidor a la entrega.
     *
     * @param repartidorIdParam Identificador del repartidor.
     * @param nombreRepartidorParam Nombre del repartidor.
     * @param telefonoRepartidorParam Teléfono del repartidor.
     */
    public void asignarRepartidor(final String repartidorIdParam, final String nombreRepartidorParam,
                                final String telefonoRepartidorParam) {
        this.repartidorId = repartidorIdParam;
        this.nombreRepartidor = nombreRepartidorParam;
        this.telefonoRepartidor = telefonoRepartidorParam;
        this.fechaAsignacion = LocalDateTime.now();
        this.estado = EstadoPedido.EN_PROCESO;
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }

    /**
     * Cambia el estado de la entrega a un nuevo estado.
     *
     * @param nuevoEstadoParam El nuevo estado al que se cambiará la entrega.
     */
    public void cambiarEstado(final EstadoPedido nuevoEstadoParam) {
        this.estado = nuevoEstadoParam;
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }

}
