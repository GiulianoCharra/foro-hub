package oracle.alura.challenge.forohub.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginUserRequest(
        @Email(message = "Debe proporcionar un correo electrónico válido.")
        @NotBlank(message = "El correo electrónico es obligatorio.")
        String email,

        @NotBlank(message = "La contraseña es obligatoria.")
        String password
) {
}