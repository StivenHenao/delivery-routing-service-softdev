package com.softdev.delivery_routing.use_cases.service;

import com.softdev.delivery_routing.domain.model.Entrega;
import com.softdev.delivery_routing.domain.model.EstadoPedido;
import com.softdev.delivery_routing.domain.repositories.EntregaRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActualizarEstadoEntregaServiceTest {

    @Test
    void actualizar_CambiaEstadoCorrectamente_SiRepartidorCoincide() {
        EntregaRepositoryPort entregaRepo = mock(EntregaRepositoryPort.class);
        Entrega entrega = mock(Entrega.class);
        when(entrega.getRepartidorId()).thenReturn("rep123");

        when(entregaRepo.findByOrdenId("orden123")).thenReturn(Optional.of(entrega));

        ActualizarEstadoEntregaService service = new ActualizarEstadoEntregaService(entregaRepo);

        boolean resultado = service.actualizar("orden123", EstadoPedido.EN_CAMINO, "rep123");

        assertTrue(resultado);
        verify(entrega).cambiarEstado(EstadoPedido.EN_CAMINO);
        verify(entregaRepo).save(entrega);
    }

    @Test
    void actualizar_NoCambiaEstado_SiRepartidorNoCoincide() {
        EntregaRepositoryPort entregaRepo = mock(EntregaRepositoryPort.class);
        Entrega entrega = mock(Entrega.class);
        when(entrega.getRepartidorId()).thenReturn("otroRep");

        when(entregaRepo.findByOrdenId("orden123")).thenReturn(Optional.of(entrega));

        ActualizarEstadoEntregaService service = new ActualizarEstadoEntregaService(entregaRepo);

        boolean resultado = service.actualizar("orden123", EstadoPedido.EN_CAMINO, "rep123");

        assertFalse(resultado);
        verify(entrega, never()).cambiarEstado(any());
        verify(entregaRepo, never()).save(any());
    }

    @Test
    void actualizar_NoHaceNada_SiEntregaNoExiste() {
        EntregaRepositoryPort entregaRepo = mock(EntregaRepositoryPort.class);
        when(entregaRepo.findByOrdenId("ordenInexistente")).thenReturn(Optional.empty());

        ActualizarEstadoEntregaService service = new ActualizarEstadoEntregaService(entregaRepo);

        boolean resultado = service.actualizar("ordenInexistente", EstadoPedido.CANCELADA, "rep123");

        assertFalse(resultado);
    }
}
