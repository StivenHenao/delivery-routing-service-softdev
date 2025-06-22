package com.softdev.delivery_routing.infrastructure.database.repositories;

import com.softdev.delivery_routing.infrastructure.database.entities.EntregaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Entrega.
 * Proporciona métodos para buscar entregas por ordenId y repartidorId.
 */
public interface JpaEntregaRepositoryDB extends JpaRepository<EntregaEntity, String> {
    /**
     * Busca una entrega por su ID de orden.
     *
     * @param ordenId el identificador de la orden asociada a la entrega
     * @return un Optional que contiene la EntregaEntity si se encuentra, o vacío si no
     */
    Optional<EntregaEntity> findByOrdenId(String ordenId);
    /**
     * Busca entregas asociadas a un repartidor por su ID.
     *
     * @param repartidorId el identificador del repartidor
     * @return una lista de EntregaEntity asociadas al repartidor
     */
    List<EntregaEntity> findByRepartidorId(String repartidorId);
}
