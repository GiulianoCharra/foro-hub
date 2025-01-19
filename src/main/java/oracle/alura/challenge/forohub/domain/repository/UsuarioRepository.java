package oracle.alura.challenge.forohub.domain.repository;

import oracle.alura.challenge.forohub.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreoElectronico(String email);
}
