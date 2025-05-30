package com.softdev.delivery_routing.use_cases.service;


import com.softdev.delivery_routing.domain.repositories.EntregaRepositoryPort;
import com.softdev.delivery_routing.use_cases.mappers.EntregaMapper;
import com.softdev.delivery_routing.use_cases.service.dtos.EntregaResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
     * Mapper para convertir una entidad Entrega a un DTO EntregaResponseDTO.
     * Facilita la conversión de datos entre la capa de dominio y la capa de presentación.
     */
    @Autowired
    @Qualifier("useCaseEntregaMapper")
    private final EntregaMapper entregaMapper;

    /**
     * Constructor del servicio de obtención de entregas.
     *
     * @param entregaRepositoryParam Repositorio de entregas para acceder a los datos de las entregas.\
     * @param entregaMapperParam Mapper para convertir entidades Entrega a DTOs EntregaResponseDTO.
     */
    public ObtenerEntregaService(final EntregaRepositoryPort entregaRepositoryParam,
                                 final EntregaMapper entregaMapperParam) {
        this.entregaRepository = entregaRepositoryParam;
        this.entregaMapper = entregaMapperParam;
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
            entrega.getFechaUltimaActualizacion(),
            entrega.getDireccion()
        );
    }

    /**
     * Obtiene una lista de entregas asociadas a un repartidor por su ID.
     * Busca las entregas en el repositorio y las convierte a una lista de DTOs.
     *
     * @param repartidorId Identificador del repartidor asociado a las entregas.
     * @return Una lista de DTOs EntregaResponseDTO con las entregas asociadas al repartidor.
     */
    public List<EntregaResponseDTO> obtenerPorRepartidorId(final String repartidorId) {
    return entregaRepository.findByRepartidorId(repartidorId)
        .stream()
        .map(entregaMapper::toResponseDTO)
        .collect(Collectors.toList());
    }
}
