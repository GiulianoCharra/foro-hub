package oracle.alura.challenge.forohub.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @NotBlank(message = "El nombre completo es obligatorio.")
        String fullName,

        @Email(message = "Debe proporcionar un correo electrónico válido.")
        @NotBlank(message = "El correo electrónico es obligatorio.")
        String email,

        @NotBlank(message = "La contraseña es obligatoria.")
        @Size(
                min = 8,
                message = "La contraseña debe tener al menos 8 caracteres."
        )
        String password
) {
}