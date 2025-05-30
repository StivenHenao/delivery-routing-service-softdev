package com.softdev.delivery_routing.domain.repositories;

import java.util.Optional;

import com.softdev.delivery_routing.domain.model.Entrega;
import com.softdev.delivery_routing.domain.model.EstadoPedido;

/**
 * Interfaz que define el puerto de repositorio para las entregas.
 * Permite guardar una entrega en el repositorio.
 */
public interface  EntregaRepositoryPort {
    /**
     * Guarda una entrega en el repositorio.
     *
     * @param entrega la entrega a guardar
     * @return la entrega guardada
     */
    Entrega save(final Entrega entrega);
    /**
     * Busca una entrega por su identificador.
     *
     * @param id el identificador de la entrega
     * @return un Optional que contiene la entrega si se encuentra, o vacío si no
     */
    Optional<Entrega> findByOrdenId(final String id);
    /**
     * Actualiza el estado de una entrega.
     *
     * @param ordenId el identificador de la orden asociada a la entrega
     * @param estado el nuevo estado del pedido
     */
    void updateEstado(final String ordenId, final EstadoPedido estado);
}
