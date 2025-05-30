package com.softdev.delivery_routing.infrastructure.adapters;

import com.softdev.delivery_routing.domain.model.Repartidor;
import com.softdev.delivery_routing.domain.repositories.RepartidorServicePort;

import lombok.Data;
import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.util.List;
import java.util.Optional;
import java.util.Random;


/**
 * Adaptador que implementa el puerto de repositorio para los repartidores,
 * utilizando WebClient para comunicarse con el user-service.
 * Permite obtener un repartidor disponible y buscar un repartidor por su identificador.
 */
@Service
public class UserServiceRepartidorAdapter implements RepartidorServicePort {
    
    /**
     * Cliente WebClient para realizar llamadas al user-service.
     * Utiliza la URL base configurada en application.properties.
     */
    private final WebClient webClient;
    /**
     * Generador de números aleatorios para seleccionar un repartidor disponible.
     * Se usa para simular la selección aleatoria de un repartidor.
     */
    private final Random random = new Random();
    
    /**
     * URL base del user-service, configurada en application.properties.
     * Permite cambiar la URL sin modificar el código.
     */
    @Value("${user-service.base-url:http://localhost:8081}")
    private String userServiceBaseUrl;

    /**
     * Constructor del adaptador de repartidores.
     * Inicializa el WebClient con la URL base del user-service.
     *
     * @param webClientBuilder Builder para crear instancias de WebClient.
     */
    public UserServiceRepartidorAdapter(final WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    /**
     * Obtiene un repartidor disponible del user-service.
     * Realiza una llamada asíncrona para obtener la lista de repartidores
     * y selecciona uno aleatoriamente.
     *
     * @return Un Optional que contiene el repartidor seleccionado, o vacío si no hay repartidores disponibles.
     */
    @Override
    public Optional<Repartidor> obtenerRepartidorDisponible() {
        try {
            // Llamada asíncrona al user-service para obtener repartidores
            List<UserServiceUserDTO> repartidores = webClient
                .get()
                .uri(userServiceBaseUrl + "/api/users/repartidores")
                .retrieve()
                .bodyToFlux(UserServiceUserDTO.class)
                .collectList()
                .block(); // Para este caso usamos block(), en producción considera usar reactive streams completamente

            if (repartidores != null && !repartidores.isEmpty()) {
                // Seleccionar repartidor aleatorio
                UserServiceUserDTO repartidorSeleccionado = repartidores.get(
                    random.nextInt(repartidores.size())
                );
                
                return Optional.of(convertirARepartidor(repartidorSeleccionado));
            }
            
            return Optional.empty();
            
        } catch (WebClientResponseException e) {
            System.err.println("Error al obtener repartidores del user-service: " + e.getMessage());
            return Optional.empty();
        } catch (Exception e) {
            System.err.println("Error inesperado al comunicarse con user-service: " + e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Obtiene un repartidor por su ID desde el user-service.
     * Realiza una llamada sincrónica para buscar el repartidor por su identificador.
     *
     * @param repartidorId Identificador del repartidor a buscar.
     * @return Un Optional que contiene el repartidor encontrado, o vacío si no se encuentra.
     */
    @Override
    public Optional<Repartidor> obtenerRepartidorPorId(final String repartidorId) {
        try {
            UserServiceUserDTO usuario = webClient
                .get()
                .uri(userServiceBaseUrl + "/api/users/{id}", repartidorId)
                .retrieve()
                .bodyToMono(UserServiceUserDTO.class)
                .block();

            if (usuario != null && "REPARTIDOR".equals(usuario.getRol())) {
                return Optional.of(convertirARepartidor(usuario));
            }
            
            return Optional.empty();
            
        } catch (WebClientResponseException e) {
            System.err.println("Repartidor no encontrado con ID: " + repartidorId);
            return Optional.empty();
        } catch (Exception e) {
            System.err.println("Error al obtener repartidor por ID: " + e.getMessage());
            return Optional.empty();
        }
    }
    
    /**
     * Convierte un UserServiceUserDTO a un objeto Repartidor.
     * Utiliza los datos del DTO para crear una instancia de Repartidor.
     *
     * @param userDto DTO del usuario obtenido del user-service.
     * @return Un objeto Repartidor con los datos del DTO.
     */
    private Repartidor convertirARepartidor(final UserServiceUserDTO userDto) {
        return new Repartidor(
            userDto.getId(),
            userDto.getNombre(),
            generatePhoneNumber(), // Generar teléfono temporal o obtenerlo de otra fuente
            userDto.getEmail()
        );
    }
    
    /**
     * Genera un número de teléfono temporal para el repartidor.
     * En producción, este valor debería provenir del user-service o de otra fuente confiable.
     *
     * @return Un número de teléfono simulado en formato +57-300-XXXXXXX.
     */
    private String generatePhoneNumber() {
        // Generar número temporal o usar valor por defecto
        // En producción, esto debería venir de user-service o otra fuente
        return "+57-300-" + String.format("%07d", random.nextInt(10000000));
    }
}

/**
 * DTO para representar un usuario del user-service.
 * Este DTO se utiliza para mapear los datos del usuario obtenidos desde el user-service.
 */
// DTO para comunicación con user-service
@Data
@Getter
class UserServiceUserDTO {
    /**
     * Identificador único del usuario.
     */
    private String id;
    /**
     * Correo electrónico del usuario.
     */
    private String email;
    /**
     * Nombre del usuario.
     */
    private String nombre;
    /**
     * Contraseña del usuario (no se utiliza en este contexto).
     * Este campo se incluye por compatibilidad, pero no se usa en la lógica del adaptador.
     */
    private String password; // No lo usamos
    /**
     * Rol del usuario (debe ser "REPARTIDOR" para los repartidores).
     */
    private String rol;

    /**
     * Constructor por defecto necesario para la deserialización del DTO.
     * Este constructor es requerido por Spring para crear instancias del DTO.
     */
    public UserServiceUserDTO() {
    }
}
