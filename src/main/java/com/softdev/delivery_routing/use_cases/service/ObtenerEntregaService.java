package com.softdev.delivery_routing.use_cases.service;


import com.softdev.delivery_routing.domain.repositories.EntregaRepositoryPort;
import com.softdev.delivery_routing.use_cases.service.dtos.EntregaResponseDTO;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.softdev.delivery_routing.domain.model.Entrega;

/**
 * Servicio para obtener una entrega por su ID de orden.
 * Utiliza el repositorio de entregas para buscar la entrega y convertirla a un DTO.
 */
@Service
public class ObtenerEntregaService {
    /**
     * Repositorio de entregas.
     * Permite buscar una entrega por su ID de orden.
     */
    private final EntregaRepositoryPort entregaRepository;

    /**
     * Constructor del servicio de obtención de entregas.
     *
     * @param entregaRepositoryParam Repositorio de entregas para acceder a los datos de las entregas.
     */
    public ObtenerEntregaService(final EntregaRepositoryPort entregaRepositoryParam) {
        this.entregaRepository = entregaRepositoryParam;
    }
    /**
     * Obtiene una entrega por su ID de orden.
     * Busca la entrega en el repositorio y la convierte a un DTO.
     *
     * @param ordenId Identificador de la orden asociada a la entrega.
     * @return Un Optional que contiene el DTO de la entrega si se encuentra, o vacío si no.
     */
    public Optional<EntregaResponseDTO> obtenerPorOrdenId(final String ordenId) {
        return entregaRepository.findByOrdenId(ordenId)
            .map(this::convertirADTO);
    }
    /**
     * Convierte una entidad Entrega a un DTO EntregaResponseDTO.
     *
     * @param entrega Entidad Entrega a convertir.
     * @return DTO EntregaResponseDTO con los datos de la entrega.
     */
    private EntregaResponseDTO convertirADTO(final Entrega entrega) {
        return new EntregaResponseDTO(
            entrega.getOrdenId(),
            entrega.getNombreCliente(),
            entrega.getEmailCliente(),
            entrega.getDniCliente(),
            entrega.getEstado(),
            entrega.getNombreRepartidor(),
            entrega.getTelefonoRepartidor(),
            entrega.getFechaUltimaActualizacion()
        );
    }
}