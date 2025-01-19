package oracle.alura.challenge.forohub.domain.repository;

import oracle.alura.challenge.forohub.domain.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
