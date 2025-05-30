package com.softdev.delivery_routing.infrastructure.adapters;

import com.softdev.delivery_routing.domain.repositories.EntregaRepositoryPort;
import com.softdev.delivery_routing.domain.model.Entrega;
import com.softdev.delivery_routing.domain.model.EstadoPedido;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementación del puerto de repositorio para las entregas utilizando una base de datos en memoria.
 * Esta clase simula el almacenamiento de entregas y permite realizar operaciones CRUD básicas.
 */
@Repository
public class JpaEntregaRepository implements EntregaRepositoryPort {
    
    /**
     * Mapa concurrente para almacenar las entregas en memoria.
     * Utiliza el ID de la orden como clave para acceder a las entregas.
     */
    private final Map<String, Entrega> entregas = new ConcurrentHashMap<>();

    /**
     * Guarda una entrega en el repositorio.
     * Si la entrega ya existe, se actualiza.
     *
     * @param entrega la entrega a guardar
     * @return la entrega guardada
     */
    @Override
    public Entrega save(Entrega entrega) {
        entregas.put(entrega.getOrdenId(), entrega);
        return entrega;
    }

    /**
     * Busca una entrega por su ID de orden.
     *
     * @param ordenId el identificador de la orden asociada a la entrega
     * @return un Optional que contiene la entrega si se encuentra, o vacío si no
     */
    @Override
    public Optional<Entrega> findByOrdenId(String ordenId) {
        return Optional.ofNullable(entregas.get(ordenId));
    }

    /**
     * Actualiza el estado de una entrega asociada a una orden.
     * Busca la entrega por su ID de orden y cambia su estado.
     *
     * @param ordenId el identificador de la orden asociada a la entrega
     * @param estado el nuevo estado del pedido
     */
    @Override
    public void updateEstado(String ordenId, EstadoPedido estado) {
        Optional<Entrega> entregaOpt = findByOrdenId(ordenId);
        if (entregaOpt.isPresent()) {
            Entrega entrega = entregaOpt.get();
            entrega.cambiarEstado(estado);
            save(entrega);
        }
    }
}
