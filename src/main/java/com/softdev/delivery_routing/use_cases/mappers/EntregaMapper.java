package com.softdev.delivery_routing.use_cases.mappers;

import com.softdev.delivery_routing.domain.model.Entrega;
import com.softdev.delivery_routing.use_cases.service.dtos.EntregaResponseDTO;
import org.springframework.stereotype.Component;

@Component("useCaseEntregaMapper")
public class EntregaMapper {

    /**
     * Mapea una entidad de Entrega a un DTO de respuesta.
     *
     * @param entrega la entidad de Entrega a mapear
     * @return un DTO de respuesta que representa la Entrega
     */
    public EntregaResponseDTO toResponseDTO(final Entrega entrega) {
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

}
