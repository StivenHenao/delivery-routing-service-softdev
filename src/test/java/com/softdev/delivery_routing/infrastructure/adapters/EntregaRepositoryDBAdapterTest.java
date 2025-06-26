package com.softdev.delivery_routing.infrastructure.adapters;

import com.softdev.delivery_routing.domain.model.Entrega;
import com.softdev.delivery_routing.domain.model.EstadoPedido;
import com.softdev.delivery_routing.infrastructure.database.entities.EntregaEntity;
import com.softdev.delivery_routing.infrastructure.database.mappers.EntregaMapper;
import com.softdev.delivery_routing.infrastructure.database.repositories.JpaEntregaRepositoryDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EntregaRepositoryDBAdapterTest {

    private JpaEntregaRepositoryDB jpaRepository;
    private EntregaMapper mapper;
    private EntregaRepositoryDBAdapter adapter;

    @BeforeEach
    void setUp() {
        jpaRepository = mock(JpaEntregaRepositoryDB.class);
        mapper = mock(EntregaMapper.class);
        adapter = new EntregaRepositoryDBAdapter(jpaRepository, mapper);
    }

    @Test
    void testSave_shouldReturnSavedEntrega() {
        Entrega entrega = new Entrega(); // tu clase de dominio
        EntregaEntity entity = new EntregaEntity();
        EntregaEntity savedEntity = new EntregaEntity();
        Entrega mapped = new Entrega();

        when(mapper.toEntity(entrega)).thenReturn(entity);
        when(jpaRepository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDomain(savedEntity)).thenReturn(mapped);

        Entrega result = adapter.save(entrega);

        assertEquals(mapped, result);
        verify(jpaRepository).save(entity);
    }

    @Test
    void testFindByOrdenId_found_shouldReturnEntrega() {
        String ordenId = "123";
        EntregaEntity entity = new EntregaEntity();
        Entrega entrega = new Entrega();

        when(jpaRepository.findByOrdenId(ordenId)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(entrega);

        Optional<Entrega> result = adapter.findByOrdenId(ordenId);

        assertTrue(result.isPresent());
        assertEquals(entrega, result.get());
    }

    @Test
    void testFindByOrdenId_notFound_shouldReturnEmpty() {
        when(jpaRepository.findByOrdenId("999")).thenReturn(Optional.empty());

        Optional<Entrega> result = adapter.findByOrdenId("999");

        assertFalse(result.isPresent());
    }

    @Test
    void testUpdateEstado_shouldUpdateAndSave() {
        String ordenId = "321";
        EstadoPedido estado = EstadoPedido.EN_PROCESO;
        EntregaEntity entity = new EntregaEntity();

        when(jpaRepository.findByOrdenId(ordenId)).thenReturn(Optional.of(entity));

        adapter.updateEstado(ordenId, estado);

        assertEquals(estado, entity.getEstado());
        assertNotNull(entity.getFechaUltimaActualizacion());
        verify(jpaRepository).save(entity);
    }

    @Test
    void testUpdateEstado_entregaNoExiste_noHaceNada() {
        when(jpaRepository.findByOrdenId("000")).thenReturn(Optional.empty());

        adapter.updateEstado("000", EstadoPedido.CANCELADA);

        verify(jpaRepository, never()).save(any());
    }

    @Test
    void testFindByRepartidorId_shouldReturnMappedList() {
        String repartidorId = "r1";
        EntregaEntity entity = new EntregaEntity();
        Entrega entrega = new Entrega();

        when(jpaRepository.findByRepartidorId(repartidorId)).thenReturn(List.of(entity));
        when(mapper.toDomain(entity)).thenReturn(entrega);

        List<Entrega> result = adapter.findByRepartidorId(repartidorId);

        assertEquals(1, result.size());
        assertEquals(entrega, result.get(0));
    }
}
