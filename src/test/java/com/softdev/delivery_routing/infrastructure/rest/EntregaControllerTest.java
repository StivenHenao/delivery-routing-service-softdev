package com.softdev.delivery_routing.infrastructure.rest;

import com.softdev.delivery_routing.use_cases.service.ActualizarEstadoEntregaService;
import com.softdev.delivery_routing.use_cases.service.ObtenerEntregaService;
import com.softdev.delivery_routing.use_cases.service.dtos.ActualizarEstadoRequestDTO;
import com.softdev.delivery_routing.use_cases.service.dtos.EntregaResponseDTO;
import com.softdev.delivery_routing.domain.model.EstadoPedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EntregaControllerTest {

    @Mock
    private ObtenerEntregaService obtenerEntregaService;

    @Mock
    private ActualizarEstadoEntregaService actualizarEstadoEntregaService;

    @InjectMocks
    private EntregaController entregaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRastrearEntrega_Existe() {
        String ordenId = "ORD123";

        EntregaResponseDTO entrega = new EntregaResponseDTO();
        entrega.setOrdenId(ordenId);
        entrega.setNombreCliente("Juan Pérez");
        entrega.setEmailCliente("juan@example.com");
        entrega.setDniCliente("12345678");
        entrega.setEstado(EstadoPedido.ENTREGADA);
        entrega.setNombreRepartidor("Pedro Repartidor");
        entrega.setTelefonoRepartidor("321654987");
        entrega.setFechaUltimaActualizacion(LocalDateTime.now());
        entrega.setDireccionEntrega("Calle Falsa 123");

        when(obtenerEntregaService.obtenerPorOrdenId(ordenId)).thenReturn(Optional.of(entrega));

        ResponseEntity<EntregaResponseDTO> response = entregaController.rastrearEntrega(ordenId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ordenId, response.getBody().getOrdenId());
    }

    @Test
    void testRastrearEntrega_NoExiste() {
        String ordenId = "ORD404";

        when(obtenerEntregaService.obtenerPorOrdenId(ordenId)).thenReturn(Optional.empty());

        ResponseEntity<EntregaResponseDTO> response = entregaController.rastrearEntrega(ordenId);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testActualizarEstadoEntrega_Success() {
        String ordenId = "ORD789";
        String repartidorId = "REP001";
        EstadoPedido nuevoEstado = EstadoPedido.valueOf("ENTREGADA");


        ActualizarEstadoRequestDTO request = new ActualizarEstadoRequestDTO();
        request.setNuevoEstado(nuevoEstado);
        request.setRepartidorId(repartidorId);

        when(actualizarEstadoEntregaService.actualizar(ordenId, nuevoEstado, repartidorId)).thenReturn(true);

        ResponseEntity<String> response = entregaController.actualizarEstadoEntrega(ordenId, request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Estado actualizado correctamente", response.getBody());
    }

    @Test
    void testActualizarEstadoEntrega_Failure() {
        String ordenId = "ORD789";
        String repartidorId = "REP001";
        EstadoPedido nuevoEstado = EstadoPedido.valueOf("CANCELADA");


        ActualizarEstadoRequestDTO request = new ActualizarEstadoRequestDTO();
        request.setNuevoEstado(nuevoEstado);
        request.setRepartidorId(repartidorId);

        when(actualizarEstadoEntregaService.actualizar(ordenId, nuevoEstado, repartidorId)).thenReturn(false);

        ResponseEntity<String> response = entregaController.actualizarEstadoEntrega(ordenId, request);

        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("No se pudo actualizar"));
    }

    @Test
    void testObtenerOrdenesPorRepartidor() {
        String repartidorId = "REP999";

        EntregaResponseDTO entrega = new EntregaResponseDTO();
        entrega.setOrdenId("ORD001");
        entrega.setNombreCliente("Cliente Test");
        entrega.setEmailCliente("cliente@test.com");
        entrega.setDniCliente("98765432");
        entrega.setEstado(EstadoPedido.EN_CAMINO);
        entrega.setNombreRepartidor("Carlos Moto");
        entrega.setTelefonoRepartidor("3001234567");
        entrega.setFechaUltimaActualizacion(LocalDateTime.now());
        entrega.setDireccionEntrega("Av. Siempre Viva 742");

        when(obtenerEntregaService.obtenerPorRepartidorId(repartidorId)).thenReturn(List.of(entrega));

        ResponseEntity<List<EntregaResponseDTO>> response = entregaController.obtenerOrdenesPorRepartidor(repartidorId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("ORD001", response.getBody().get(0).getOrdenId());
    }
}
