package com.softdev.delivery_routing.use_cases.service;

import org.springframework.stereotype.Service;

import com.softdev.delivery_routing.domain.model.EstadoPedido;
import com.softdev.delivery_routing.domain.repositories.EntregaRepositoryPort;

@Service
public class ActualizarEstadoEntregaService {
    private final EntregaRepositoryPort entregaRepository;

    /**
     * Caso de uso para actualizar el estado de una entrega.
     * Permite cambiar el estado de un pedido y verificar que el repartidor asignado es el correcto.
     * 
     * @param entregaRepositoryParam Repositorio de entregas para acceder a los datos de las entregas.
     */
    public ActualizarEstadoEntregaService(final EntregaRepositoryPort entregaRepositoryParam) {
        this.entregaRepository = entregaRepositoryParam;
    }

    /**
     * Actualiza el estado de una entrega asociada a una orden.
     * Verifica que el repartidor asignado es el correcto antes de realizar la actualización.
     *
     * @param ordenId Identificador de la orden asociada a la entrega.
     * @param nuevoEstado Nuevo estado del pedido.
     * @param repartidorId Identificador del repartidor asignado a la entrega.
     * @return true si se actualizó correctamente, false si no se encontró la entrega o el repartidor no coincide.
     */
    public boolean actualizar(final String ordenId, final EstadoPedido nuevoEstado, final String repartidorId) {
        return entregaRepository.findByOrdenId(ordenId)
            .filter(entrega -> entrega.getRepartidorId().equals(repartidorId))
            .map(entrega -> {
                entrega.cambiarEstado(nuevoEstado);
                entregaRepository.save(entrega);
                return true;
            })
            .orElse(false);
    }
}
