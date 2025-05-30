package com.softdev.delivery_routing.domain.repositories;

import java.util.Optional;

import com.softdev.delivery_routing.domain.model.Repartidor;

/**
 * Interfaz que define el puerto de repositorio para los repartidores.
 * Permite obtener un repartidor disponible y buscar un repartidor por su identificador.
 */
public interface RepartidorServicePort {
    /**
     * Guarda un repartidor en el repositorio.
     *
     * @param repartidor el repartidor a guardar
     * @return el repartidor guardado
     */
    Optional<Repartidor> obtenerRepartidorDisponible();
    /**
     * Busca un repartidor por su identificador.
     *
     * @param id el identificador del repartidor
     * @return un Optional que contiene el repartidor si se encuentra, o vacío si no
     */
    Optional<Repartidor> obtenerRepartidorPorId(final String id);
}
