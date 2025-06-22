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
    private String dni;
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
     * @param dniParam       Identificador del repartidor.
     * @param nombreParam   Nombre del repartidor.
     * @param telefonoParam Número de teléfono del repartidor.
     * @param emailParam    Correo electrónico del repartidor.
     */
    public Repartidor(final String dniParam, final String nombreParam, final String telefonoParam,
                    final String emailParam) {
        this.dni = dniParam;
        this.nombre = nombreParam;
        this.telefono = telefonoParam;
        this.email = emailParam;
        this.disponible = true;
    }

}
