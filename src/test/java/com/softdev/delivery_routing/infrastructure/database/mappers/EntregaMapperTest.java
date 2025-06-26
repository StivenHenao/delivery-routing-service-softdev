package com.softdev.delivery_routing.infrastructure.database.mappers;

import com.softdev.delivery_routing.domain.model.Entrega;
import com.softdev.delivery_routing.domain.model.EstadoPedido;
import com.softdev.delivery_routing.infrastructure.database.entities.EntregaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntregaMapperTest {

    private EntregaMapper entregaMapper;

    @BeforeEach
    void setUp() {
        entregaMapper = new EntregaMapper();
    }

    @Test
    void testToEntity() {
        Entrega entrega = new Entrega();
        entrega.setId("E123");
        entrega.setOrdenId("ORD456");
        entrega.setEmailCliente("cliente@email.com");
        entrega.setNombreCliente("Juan Pérez");
        entrega.setDniCliente("12345678");
        entrega.setDetalles(List.of("Producto A", "Producto B"));
        entrega.setMetodoPago("Tarjeta");
        entrega.setValorTotal(new BigDecimal("199.99"));
        entrega.setFechaPedido(LocalDateTime.now());
        entrega.setEstado(EstadoPedido.EN_CAMINO);
        entrega.setRepartidorId("REP789");
        entrega.setNombreRepartidor("Carlos Ruiz");
        entrega.setTelefonoRepartidor("3001234567");
        entrega.setFechaAsignacion(LocalDateTime.now());
        entrega.setFechaUltimaActualizacion(LocalDateTime.now());
        entrega.setDireccion("Calle Falsa 123");

        EntregaEntity entity = entregaMapper.toEntity(entrega);

        assertEquals(entrega.getId(), entity.getId());
        assertEquals(entrega.getOrdenId(), entity.getOrdenId());
        assertEquals(entrega.getEmailCliente(), entity.getEmailCliente());
        assertEquals(entrega.getNombreCliente(), entity.getNombreCliente());
        assertEquals(entrega.getDniCliente(), entity.getDniCliente());
        assertEquals(entrega.getDetalles(), entity.getDetalles());
        assertEquals(entrega.getMetodoPago(), entity.getMetodoPago());
        assertEquals(entrega.getValorTotal(), entity.getValorTotal());
        assertEquals(entrega.getEstado(), entity.getEstado());
        assertEquals(entrega.getRepartidorId(), entity.getRepartidorId());
        assertEquals(entrega.getNombreRepartidor(), entity.getNombreRepartidor());
        assertEquals(entrega.getTelefonoRepartidor(), entity.getTelefonoRepartidor());
        assertEquals(entrega.getDireccion(), entity.getDireccion());
    }

    @Test
    void testToDomain() {
        EntregaEntity entity = new EntregaEntity();
        entity.setId("E999");
        entity.setOrdenId("ORD999");
        entity.setEmailCliente("otro@email.com");
        entity.setNombreCliente("Ana López");
        entity.setDniCliente("87654321");
        entity.setDetalles(List.of("Producto C"));
        entity.setMetodoPago("Efectivo");
        entity.setValorTotal(new BigDecimal("59.50"));
        entity.setFechaPedido(LocalDateTime.now());
        entity.setEstado(EstadoPedido.ENTREGADA);
        entity.setRepartidorId("REP321");
        entity.setNombreRepartidor("Laura Méndez");
        entity.setTelefonoRepartidor("3019876543");
        entity.setFechaAsignacion(LocalDateTime.now());
        entity.setFechaUltimaActualizacion(LocalDateTime.now());
        entity.setDireccion("Avenida Siempreviva 742");

        Entrega entrega = entregaMapper.toDomain(entity);

        assertEquals(entity.getId(), entrega.getId());
        assertEquals(entity.getOrdenId(), entrega.getOrdenId());
        assertEquals(entity.getEmailCliente(), entrega.getEmailCliente());
        assertEquals(entity.getNombreCliente(), entrega.getNombreCliente());
        assertEquals(entity.getDniCliente(), entrega.getDniCliente());
        assertEquals(entity.getDetalles(), entrega.getDetalles());
        assertEquals(entity.getMetodoPago(), entrega.getMetodoPago());
        assertEquals(entity.getValorTotal(), entrega.getValorTotal());
        assertEquals(entity.getEstado(), entrega.getEstado());
        assertEquals(entity.getRepartidorId(), entrega.getRepartidorId());
        assertEquals(entity.getNombreRepartidor(), entrega.getNombreRepartidor());
        assertEquals(entity.getTelefonoRepartidor(), entrega.getTelefonoRepartidor());
        assertEquals(entity.getDireccion(), entrega.getDireccion());
    }
}
