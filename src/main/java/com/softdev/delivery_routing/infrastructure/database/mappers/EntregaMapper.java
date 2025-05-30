package com.softdev.delivery_routing.infrastructure.database.mappers;

import com.softdev.delivery_routing.domain.model.Entrega;
import com.softdev.delivery_routing.infrastructure.database.entities.EntregaEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre la entidad EntregaEntity y el modelo de dominio Entrega.
 * Facilita la conversión de datos entre la capa de persistencia y la capa de dominio.
 */
@Component("dbEntregaMapper")
public class EntregaMapper {

    /**
     * Convierte un objeto Entrega a una entidad EntregaEntity.
     *
     * @param entrega Objeto de dominio Entrega a convertir.
     * @return EntregaEntity con los datos del objeto Entrega.
     */
    public EntregaEntity toEntity(final Entrega entrega) {
        EntregaEntity entity = new EntregaEntity();
        entity.setId(entrega.getId());
        entity.setOrdenId(entrega.getOrdenId());
        entity.setEmailCliente(entrega.getEmailCliente());
        entity.setNombreCliente(entrega.getNombreCliente());
        entity.setDniCliente(entrega.getDniCliente());
        entity.setDetalles(entrega.getDetalles());
        entity.setMetodoPago(entrega.getMetodoPago());
        entity.setValorTotal(entrega.getValorTotal());
        entity.setFechaPedido(entrega.getFechaPedido());
        entity.setEstado(entrega.getEstado());
        entity.setRepartidorId(entrega.getRepartidorId());
        entity.setNombreRepartidor(entrega.getNombreRepartidor());
        entity.setTelefonoRepartidor(entrega.getTelefonoRepartidor());
        entity.setFechaAsignacion(entrega.getFechaAsignacion());
        entity.setFechaUltimaActualizacion(entrega.getFechaUltimaActualizacion());
        entity.setDireccion(entrega.getDireccion());
        return entity;
    }

    /**
     * Convierte una entidad EntregaEntity a un objeto de dominio Entrega.
     *
     * @param entity Entidad EntregaEntity a convertir.
     * @return Objeto de dominio Entrega con los datos de la entidad.
     */
    public Entrega toDomain(final EntregaEntity entity) {
        Entrega entrega = new Entrega();
        entrega.setId(entity.getId());
        entrega.setOrdenId(entity.getOrdenId());
        entrega.setEmailCliente(entity.getEmailCliente());
        entrega.setNombreCliente(entity.getNombreCliente());
        entrega.setDniCliente(entity.getDniCliente());
        entrega.setDetalles(entity.getDetalles());
        entrega.setMetodoPago(entity.getMetodoPago());
        entrega.setValorTotal(entity.getValorTotal());
        entrega.setFechaPedido(entity.getFechaPedido());
        entrega.setEstado(entity.getEstado());
        entrega.setRepartidorId(entity.getRepartidorId());
        entrega.setNombreRepartidor(entity.getNombreRepartidor());
        entrega.setTelefonoRepartidor(entity.getTelefonoRepartidor());
        entrega.setFechaAsignacion(entity.getFechaAsignacion());
        entrega.setFechaUltimaActualizacion(entity.getFechaUltimaActualizacion());
        entrega.setDireccion(entity.getDireccion());
        return entrega;
    }
}
