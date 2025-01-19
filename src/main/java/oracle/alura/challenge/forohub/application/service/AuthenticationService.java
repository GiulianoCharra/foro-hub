package oracle.alura.challenge.forohub.application.service;

import oracle.alura.challenge.forohub.application.dto.request.LoginUserRequest;
import oracle.alura.challenge.forohub.application.dto.request.RegisterUserRequest;
import oracle.alura.challenge.forohub.domain.entity.Usuario;
import oracle.alura.challenge.forohub.domain.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager
                                ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public Usuario signup(RegisterUserRequest dto) {
        if (usuarioRepository.findByCorreoElectronico(dto.email())
                             .isPresent()) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado.");
        }

        Usuario usuario = new Usuario(dto.fullName(),
                                      dto.email(),
                                      passwordEncoder.encode(dto.password())
        );

        return usuarioRepository.save(usuario);
    }

    public Usuario authenticate(LoginUserRequest dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(),
                                                        dto.password()
                )
                                          );

        return usuarioRepository.findByCorreoElectronico(dto.email())
                                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
    }
}