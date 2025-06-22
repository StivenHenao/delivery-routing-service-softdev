package com.softdev.delivery_routing.use_cases.service;

import org.springframework.stereotype.Service;

import com.softdev.delivery_routing.domain.model.Entrega;
import com.softdev.delivery_routing.domain.repositories.EntregaRepositoryPort;
import com.softdev.delivery_routing.domain.repositories.RepartidorServicePort;
import com.softdev.delivery_routing.use_cases.service.dtos.OrdenConDetallesDTO;

import java.util.UUID;



@Service
public class ProcesarOrdenService {
    /**
     * Caso de uso para procesar una orden y crear una entrega.
     * Asigna un repartidor disponible a la entrega y guarda la entrega en el repositorio.
     */
    private final EntregaRepositoryPort entregaRepository;
    /**
     * Servicio para gestionar repartidores.
     * Permite obtener un repartidor disponible para asignarlo a la entrega.
     */
    private final RepartidorServicePort repartidorService;

    /**
     * Constructor del servicio de procesamiento de órdenes.
     *
     * @param entregaRepositoryParam Repositorio de entregas para acceder a los datos de las entregas.
     * @param repartidorServiceParam Servicio para gestionar repartidores y asignar uno disponible a la entrega.
     */
    public ProcesarOrdenService(final EntregaRepositoryPort entregaRepositoryParam, final RepartidorServicePort repartidorServiceParam) {
        this.entregaRepository = entregaRepositoryParam;
        this.repartidorService = repartidorServiceParam;
    }

    /**
     * Procesa una orden y crea una entrega.
     * Asigna un repartidor disponible a la entrega y guarda la entrega en el repositorio.
     *
     * @param ordenDto DTO que contiene los detalles de la orden a procesar.
     */
    public void procesar(final OrdenConDetallesDTO ordenDto) {
        // Crear la entrega
        Entrega entrega = new Entrega(
            ordenDto.getId(),
            ordenDto.getEmailCliente(),
            ordenDto.getNombreCliente(),
            ordenDto.getDniCliente(),
            ordenDto.getDireccion(),
            ordenDto.getDetalles(),
            ordenDto.getMetodoPago(),
            ordenDto.getValorTotal(),
            ordenDto.getFechaPedido()
        );

        entrega.setId(UUID.randomUUID().toString());

        // Asignar repartidor disponible
        repartidorService.obtenerRepartidorDisponible(entrega.getOrdenId())
            .ifPresentOrElse(repartidor -> {
                entrega.asignarRepartidor(
                    repartidor.getDni(),
                    repartidor.getNombre(),
                    repartidor.getTelefono()
                );
                entregaRepository.save(entrega);
            }, () -> {
                throw new IllegalStateException("No hay repartidores disponibles para procesar la orden: " + ordenDto.getId());
            });

        System.out.println("Entrega procesada y asignada: " + entrega.getOrdenId());
    }
}
