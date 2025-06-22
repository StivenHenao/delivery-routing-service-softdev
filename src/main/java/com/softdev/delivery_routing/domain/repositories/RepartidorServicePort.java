package com.softdev.delivery_routing.domain.repositories;

import java.util.Optional;

import com.softdev.delivery_routing.domain.model.Repartidor;

/**
 * Interfaz que define el puerto de repositorio para los repartidores.
 * Permite obtener un repartidor disponible y buscar un repartidor por su identificador.
 */
public interface RepartidorServicePort {

    /**
     * Obtiene un repartidor disponible para asignar a una orden.
     * Realiza una llamada al user-service para obtener la lista de repartidores
     * y selecciona uno basado en el hash del ID de la orden.
     *
     * @param ordenId el identificador de la orden para la cual se busca un repartidor
     * @return un Optional que contiene el repartidor disponible si se encuentra, o vacío si no
     */
    Optional<Repartidor> obtenerRepartidorDisponible(String ordenId);
    /**
     * Busca un repartidor por su identificador.
     *
     * @param id el identificador del repartidor
     * @return un Optional que contiene el repartidor si se encuentra, o vacío si no
     */
    Optional<Repartidor> obtenerRepartidorPorId(String id);
}
