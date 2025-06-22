package com.softdev.delivery_routing.infrastructure.database.entities;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.softdev.delivery_routing.domain.model.EstadoPedido;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase que representa una entrega de un pedido.
 * Esta entidad se utiliza para almacenar información sobre las entregas en la base de datos.
 */
@Entity
@Table(name = "entregas")
@Data
public class EntregaEntity {
    /**
     * Identificador único de la entrega.
     */
    @Id
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
     * Detalles del pedido, como productos y cantidades.
     * Utiliza una colección de elementos para almacenar los detalles.
     */
    @ElementCollection
    private List<String> detalles;  // Cuidado con listas: lo veremos más abajo
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
     * Utiliza un enumerado para representar los diferentes estados del pedido.
     */
    @Enumerated(EnumType.STRING)
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
     * Dirección de entrega del pedido.
     */
    private String direccion;

    /**
     * Constructor por defecto requerido por JPA.
    */
    public EntregaEntity() {
    }
}
