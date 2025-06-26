package com.softdev.delivery_routing.use_cases.mappers;

import com.softdev.delivery_routing.domain.model.Entrega;
import com.softdev.delivery_routing.domain.model.EstadoPedido;
import com.softdev.delivery_routing.use_cases.service.dtos.EntregaResponseDTO;
import com.softdev.delivery_routing.use_cases.service.dtos.OrdenConDetallesDTO;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class EntregaMapperTest {

    private final EntregaMapper mapper = new EntregaMapper();

    @Test
    void testToResponseDTO() {
        // Arrange
        OrdenConDetallesDTO.MetodoPagoFacturaDTO metodoPago = new OrdenConDetallesDTO.MetodoPagoFacturaDTO();
        metodoPago.setNombre("Tarjeta");
        Entrega entrega = new Entrega(
            "ORD123",
            "cliente@email.com",
            "Carlos Pérez",
            "123456789",
            "Calle Falsa 123",
            Collections.emptyList(), // detalles
            metodoPago, // metodoPago
            BigDecimal.valueOf(100.0), // valorTotal
            LocalDateTime.now()
        );
        entrega.asignarRepartidor("987654321", "Repartidor Juan", "3123456789");
        entrega.cambiarEstado(EstadoPedido.EN_CAMINO);

        // Act
        EntregaResponseDTO dto = mapper.toResponseDTO(entrega);

        // Assert
        assertEquals(entrega.getOrdenId(), dto.getOrdenId());
        assertEquals(entrega.getNombreCliente(), dto.getNombreCliente());
        assertEquals(entrega.getEmailCliente(), dto.getEmailCliente());
        assertEquals(entrega.getDniCliente(), dto.getDniCliente());
        assertEquals(entrega.getEstado(), dto.getEstado());
        assertEquals(entrega.getNombreRepartidor(), dto.getNombreRepartidor());
        assertEquals(entrega.getTelefonoRepartidor(), dto.getTelefonoRepartidor());
        assertEquals(entrega.getFechaUltimaActualizacion(), dto.getFechaUltimaActualizacion());
        assertEquals(entrega.getDireccion(), dto.getDireccionEntrega());
    }
}
