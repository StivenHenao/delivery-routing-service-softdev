package com.softdev.delivery_routing.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Repartidor {
    /**
     * Identificador único del repartidor.
     */
    private String id;
    /**
     * Nombre del repartidor.
     */
    private String nombre;
/**
     * Número de teléfono del repartidor.
     */
    private String telefono;
    /**
     * Correo electrónico del repartidor.
     */
    private String email;
    /**
     * Indica si el repartidor está disponible para recibir pedidos.
     */
    private boolean disponible;

    /**
     * Constructor por defecto.
     */
    public Repartidor() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param idParam       Identificador del repartidor.
     * @param nombreParam   Nombre del repartidor.
     * @param telefonoParam Número de teléfono del repartidor.
     * @param emailParam    Correo electrónico del repartidor.
     */
    public Repartidor(final String idParam, final String nombreParam, final String telefonoParam,
                    final String emailParam) {
        this.id = idParam;
        this.nombre = nombreParam;
        this.telefono = telefonoParam;
        this.email = emailParam;
        this.disponible = true;
    }

}
