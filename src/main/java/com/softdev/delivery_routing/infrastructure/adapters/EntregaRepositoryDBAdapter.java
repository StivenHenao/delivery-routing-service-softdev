package com.softdev.delivery_routing.infrastructure.adapters;

import com.softdev.delivery_routing.domain.model.Entrega;
import com.softdev.delivery_routing.domain.model.EstadoPedido;
import com.softdev.delivery_routing.domain.repositories.EntregaRepositoryPort;
import com.softdev.delivery_routing.infrastructure.database.entities.EntregaEntity;
import com.softdev.delivery_routing.infrastructure.database.mappers.EntregaMapper;
import com.softdev.delivery_routing.infrastructure.database.repositories.JpaEntregaRepositoryDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EntregaRepositoryDBAdapter implements EntregaRepositoryPort {
    /**
     * Repositorio JPA para la entidad Entrega.
     */
    private final JpaEntregaRepositoryDB jpaRepository;
    /**
     * Mapper para convertir entre la entidad EntregaEntity y el modelo de dominio Entrega.
     */
    @Autowired
    @Qualifier("dbEntregaMapper")
    private final EntregaMapper mapper;

    /**
     * Constructor del adaptador de repositorio de entregas.
     *
     * @param jpaRepositoryParam Repositorio JPA para acceder a los datos de las entregas.
     * @param mapperParam Mapper para convertir entre entidades y modelos de dominio.
     */
    public EntregaRepositoryDBAdapter(final JpaEntregaRepositoryDB jpaRepositoryParam, final EntregaMapper mapperParam) {
        this.jpaRepository = jpaRepositoryParam;
        this.mapper = mapperParam;
    }

    /**
     * Guarda una entrega en el repositorio.
     *
     * @param entrega Entrega a guardar.
     * @return Entrega guardada con su ID asignado.
     */
    @Override
    public Entrega save(final Entrega entrega) {
        EntregaEntity entity = mapper.toEntity(entrega);
        EntregaEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    /**
     * Busca una entrega por su ID de orden.
     *
     * @param ordenId Identificador de la orden asociada a la entrega.
     * @return Un Optional que contiene la Entrega si se encuentra, o vacío si no.
     */
    @Override
    public Optional<Entrega> findByOrdenId(final String ordenId) {
        return jpaRepository.findByOrdenId(ordenId)
                .map(mapper::toDomain);
    }

    /**
     * Actualiza el estado de una entrega.
     *
     * @param ordenId Identificador de la orden asociada a la entrega.
     * @param estado Nuevo estado del pedido.
     */
    @Override
    public void updateEstado(final String ordenId, final EstadoPedido estado) {
        Optional<EntregaEntity> entregaOpt = jpaRepository.findByOrdenId(ordenId);
        entregaOpt.ifPresent(entity -> {
            entity.setEstado(estado);
            entity.setFechaUltimaActualizacion(java.time.LocalDateTime.now());
            jpaRepository.save(entity);
        });
    }

    /**
     * Busca entregas asociadas a un repartidor por su ID.
     *
     * @param repartidorId Identificador del repartidor.
     * @return Lista de entregas asociadas al repartidor.
     */
    @Override
    public List<Entrega> findByRepartidorId(final String repartidorId) {
        return jpaRepository.findByRepartidorId(repartidorId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
