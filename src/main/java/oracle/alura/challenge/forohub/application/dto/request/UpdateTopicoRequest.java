package oracle.alura.challenge.forohub.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTopicoRequest(
        @NotBlank(message = "El título no puede estar vacío.")
        @Size(
                max = 150,
                message = "El título no puede exceder los 150 caracteres."
        )
        String titulo,

        @NotBlank(message = "El mensaje no puede estar vacío.")
        @Size(
                max = 255,
                message = "El mensaje no puede exceder los 255 caracteres."
        )
        String mensaje,

        @NotNull(message = "El ID del curso no puede estar vacío.")
        Long cursoId
) {
}