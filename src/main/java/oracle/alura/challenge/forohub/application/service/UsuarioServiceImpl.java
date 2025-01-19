package oracle.alura.challenge.forohub.application.service;

import oracle.alura.challenge.forohub.domain.entity.Usuario;
import oracle.alura.challenge.forohub.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correoElectronico) {
        return usuarioRepository.findByCorreoElectronico(correoElectronico);
    }

    @Override
    public List<Usuario> findAllUsers() {
        return usuarioRepository.findAll();
    }
}