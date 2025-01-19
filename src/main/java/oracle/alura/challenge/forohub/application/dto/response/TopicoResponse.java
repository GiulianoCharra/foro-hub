package oracle.alura.challenge.forohub.application.dto.response;

import java.time.LocalDateTime;

public record TopicoResponse(
        Integer id,
        String titulo,
        String mensaje,
        String status,
        LocalDateTime fechaCreacion,
        String autorNombre,
        String cursoNombre
) {
}