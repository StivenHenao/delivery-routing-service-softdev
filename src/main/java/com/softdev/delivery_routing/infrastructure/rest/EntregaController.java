package com.softdev.delivery_routing.infrastructure.rest;

import com.softdev.delivery_routing.use_cases.service.ActualizarEstadoEntregaService;
import com.softdev.delivery_routing.use_cases.service.ObtenerEntregaService;
import com.softdev.delivery_routing.use_cases.service.dtos.ActualizarEstadoRequestDTO;
import com.softdev.delivery_routing.use_cases.service.dtos.EntregaResponseDTO;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para manejar las operaciones relacionadas con la entrega de pedidos.
 * Permite rastrear el estado de una entrega y actualizar su estado.
 */
@RestController
@RequestMapping("/delivery")
public class EntregaController {

    /**
     * Servicios utilizados para obtener y actualizar el estado de las entregas.
     */
    private final ObtenerEntregaService obtenerEntregaService;
    /**
     * Servicio para actualizar el estado de una entrega.
     */
    private final ActualizarEstadoEntregaService actualizarEstadoEntregaService;

    /**
     * Constructor del controlador de entrega.
     *
     * @param obtenerEntregaServiceParam Servicio para obtener una entrega por su ID de orden.
     * @param actualizarEstadoEntregaServiceParam Servicio para actualizar el estado de una entrega.
     */
    public EntregaController(final ObtenerEntregaService obtenerEntregaServiceParam,
                           final ActualizarEstadoEntregaService actualizarEstadoEntregaServiceParam) {
        this.obtenerEntregaService = obtenerEntregaServiceParam;
        this.actualizarEstadoEntregaService = actualizarEstadoEntregaServiceParam;
    }

    /**
     * Endpoint para rastrear el estado de una entrega por su ID de orden.
     *
     * @param ordenId Identificador de la orden asociada a la entrega.
     * @return ResponseEntity con el DTO de la entrega si se encuentra, o NotFound si no.
     */
    @GetMapping("/rastrear/{ordenId}")
    public ResponseEntity<EntregaResponseDTO> rastrearEntrega(final @PathVariable String ordenId) {
        return obtenerEntregaService.obtenerPorOrdenId(ordenId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para actualizar el estado de una entrega.
     * Permite cambiar el estado de un pedido y verificar que el repartidor asignado es el correcto.
     *
     * @param ordenId Identificador de la orden asociada a la entrega.
     * @param request DTO con el nuevo estado y el ID del repartidor.
     * @return ResponseEntity con un mensaje de éxito o error.
     */
    @PutMapping("/rastrear/{ordenId}")
    public ResponseEntity<String> actualizarEstadoEntrega(
            final @PathVariable String ordenId,
            final @RequestBody ActualizarEstadoRequestDTO request) {

        boolean actualizado = actualizarEstadoEntregaService.actualizar(
            ordenId,
            request.getNuevoEstado(),
            request.getRepartidorId()
        );

        if (actualizado) {
            return ResponseEntity.ok("Estado actualizado correctamente");
        } else {
            return ResponseEntity.badRequest()
                .body("No se pudo actualizar el estado. Verifique el ID de la orden y repartidor.");
        }
    }

    /**
     * Endpoint para obtener todas las órdenes asignadas a un repartidor específico.
     *
     * @param repartidorId Identificador del repartidor.
     * @return ResponseEntity con una lista de DTOs de entregas asignadas al repartidor.
     */
    @GetMapping("/repartidor/{repartidorId}")
    public ResponseEntity<List<EntregaResponseDTO>> obtenerOrdenesPorRepartidor(final @PathVariable String repartidorId) {
        List<EntregaResponseDTO> entregas = obtenerEntregaService.obtenerPorRepartidorId(repartidorId);
        return ResponseEntity.ok(entregas);
    }
}
