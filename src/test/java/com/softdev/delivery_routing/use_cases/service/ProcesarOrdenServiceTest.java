package com.softdev.delivery_routing.use_cases.service;

import com.softdev.delivery_routing.domain.model.Entrega;
import com.softdev.delivery_routing.domain.model.Repartidor;
import com.softdev.delivery_routing.domain.repositories.EntregaRepositoryPort;
import com.softdev.delivery_routing.domain.repositories.RepartidorServicePort;
import com.softdev.delivery_routing.use_cases.service.dtos.OrdenConDetallesDTO;

// import antlr.collections.List; // Removed incorrect import
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ProcesarOrdenServiceTest {

    private EntregaRepositoryPort entregaRepository;
    private RepartidorServicePort repartidorService;
    private ProcesarOrdenService procesarOrdenService;

    @BeforeEach
    void setUp() {
        entregaRepository = Mockito.mock(EntregaRepositoryPort.class);
        repartidorService = Mockito.mock(RepartidorServicePort.class);
        procesarOrdenService = new ProcesarOrdenService(entregaRepository, repartidorService);
    }


@Test
void testProcesarOrdenConRepartidorDisponible() {
    // Arrange
    OrdenConDetallesDTO ordenDto = new OrdenConDetallesDTO();
    ordenDto.setId("ORD-123");
    ordenDto.setEmailCliente("cliente@example.com");
    ordenDto.setNombreCliente("Cliente Prueba");
    ordenDto.setDniCliente("99999999");
    ordenDto.setDireccion("Calle 123");

    // ✅ Crear lista de DetalleFacturaDTO
    OrdenConDetallesDTO.DetalleFacturaDTO detalle = new OrdenConDetallesDTO.DetalleFacturaDTO();
    detalle.setNombreProducto("Pizza");
    detalle.setCantidad(2);
    detalle.setPrecioUnitario(20000.0);
    detalle.setSubtotal(40000.0);
    ordenDto.setDetalles(List.of(detalle));

    // ✅ Crear objeto MetodoPagoFacturaDTO
    OrdenConDetallesDTO.MetodoPagoFacturaDTO metodoPago = new OrdenConDetallesDTO.MetodoPagoFacturaDTO();
    metodoPago.setNombre("Efectivo");
    ordenDto.setMetodoPago(metodoPago);

    // ✅ Usar BigDecimal para el valor total
    ordenDto.setValorTotal(BigDecimal.valueOf(40000.0));

    ordenDto.setFechaPedido(LocalDateTime.now());

    Repartidor repartidor = new Repartidor("123", "Juan", "3214567890", "juan@example.com");

    when(repartidorService.obtenerRepartidorDisponible(anyString())).thenReturn(Optional.of(repartidor));

    // Act
    procesarOrdenService.procesar(ordenDto);

    // Assert
    verify(entregaRepository).save(any(Entrega.class));
}

}
