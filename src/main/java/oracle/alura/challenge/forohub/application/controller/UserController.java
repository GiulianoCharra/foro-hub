package oracle.alura.challenge.forohub.application.controller;

import oracle.alura.challenge.forohub.application.service.UsuarioService;
import oracle.alura.challenge.forohub.domain.entity.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    private final UsuarioService userService;

    public UserController(UsuarioService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<Usuario> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();
        Usuario currentUser = (Usuario) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }
}