package oracle.alura.challenge.forohub.application.dto.response;

public record LoginResponse(
        String email,
        String token,
        long expiration
) {
}
