package oracle.alura.challenge.forohub.application.service;

import oracle.alura.challenge.forohub.domain.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> buscarPorCorreo(String correoElectronico);

    List<Usuario> findAllUsers();
}