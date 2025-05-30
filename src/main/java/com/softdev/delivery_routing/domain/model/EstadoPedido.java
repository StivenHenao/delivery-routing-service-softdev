package com.softdev.delivery_routing.domain.model;

/**
 * Enum que representa los diferentes estados de un pedido.
 */
public enum EstadoPedido {
    /**
     * Pedido recién creado.
     */
    CREADA,
    /**
     * Pedido en proceso de preparación.
     */
    EN_PROCESO,
    /**
     * Pedido en camino hacia el cliente.
     */
    EN_CAMINO,
    /**
     * Pedido entregado al cliente.
     */
    ENTREGADA,
    /**
     * Pedido cancelado.
     */
    CANCELADA
}
