package oracle.alura.challenge.forohub.domain.repository;

import oracle.alura.challenge.forohub.domain.entity.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByTituloAndMensaje(
            String titulo,
            String mensaje
                                           );

    @Query(
            "SELECT t FROM Topico t WHERE " +
                    "(:nombreCurso IS NULL OR t.curso.nombre = :nombreCurso) AND " +
                    "(:anio IS NULL OR YEAR(t.fechaCreacion) = :anio)"
    )
    Page<Topico> findByCursoAndAnio(
            @Param("nombreCurso") String nombreCurso,
            @Param("anio") Integer anio,
            Pageable pageable
                                   );
}