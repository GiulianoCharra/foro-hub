package oracle.alura.challenge.forohub.application.service;

import oracle.alura.challenge.forohub.application.dto.request.CreateTopicoRequest;
import oracle.alura.challenge.forohub.application.dto.request.UpdateTopicoRequest;
import oracle.alura.challenge.forohub.application.dto.response.TopicoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicoService {
    TopicoResponse registrarTopico(CreateTopicoRequest request);

    Page<TopicoResponse> listarTopicos(
            String nombreCurso,
            Integer anio,
            Pageable pageable
                                      );

    TopicoResponse obtenerDetalleTopico(Long id);

    void actualizarTopico(
            Long id,
            UpdateTopicoRequest request
                         );

    void eliminarTopico(Long id);
}
