package com.softdev.delivery_routing.use_cases.service;

import com.softdev.delivery_routing.domain.model.Entrega;
import com.softdev.delivery_routing.domain.repositories.EntregaRepositoryPort;
import com.softdev.delivery_routing.use_cases.mappers.EntregaMapper;
import com.softdev.delivery_routing.use_cases.service.dtos.EntregaResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ObtenerEntregaServiceTest {

    @Test
    void obtenerPorOrdenId_devuelveEntregaResponseDTO_siExiste() {
        EntregaRepositoryPort repo = mock(EntregaRepositoryPort.class);
        EntregaMapper mapper = mock(EntregaMapper.class);
        Entrega entrega = mock(Entrega.class);
        EntregaResponseDTO dto = new EntregaResponseDTO();

        when(repo.findByOrdenId("orden1")).thenReturn(Optional.of(entrega));
        when(mapper.toResponseDTO(entrega)).thenReturn(dto);

        ObtenerEntregaService service = new ObtenerEntregaService(repo, mapper);

        Optional<EntregaResponseDTO> result = service.obtenerPorOrdenId("orden1");

        assertTrue(result.isPresent());
        assertEquals(dto, result.get());
    }

    @Test
    void obtenerPorOrdenId_devuelveOptionalVacio_siNoExiste() {
        EntregaRepositoryPort repo = mock(EntregaRepositoryPort.class);
        EntregaMapper mapper = mock(EntregaMapper.class);
        when(repo.findByOrdenId("ordenInexistente")).thenReturn(Optional.empty());

        ObtenerEntregaService service = new ObtenerEntregaService(repo, mapper);

        Optional<EntregaResponseDTO> result = service.obtenerPorOrdenId("ordenInexistente");

        assertTrue(result.isEmpty());
    }

    @Test
    void obtenerPorRepartidorId_devuelveListaDeDTOs() {
        EntregaRepositoryPort repo = mock(EntregaRepositoryPort.class);
        EntregaMapper mapper = mock(EntregaMapper.class);
        Entrega entrega = mock(Entrega.class);
        EntregaResponseDTO dto = new EntregaResponseDTO();

        when(repo.findByRepartidorId("rep1")).thenReturn(List.of(entrega));
        when(mapper.toResponseDTO(entrega)).thenReturn(dto);

        ObtenerEntregaService service = new ObtenerEntregaService(repo, mapper);

        List<EntregaResponseDTO> result = service.obtenerPorRepartidorId("rep1");

        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));
    }
}
