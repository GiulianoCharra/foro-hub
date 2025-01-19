package oracle.alura.challenge.forohub.application.service;

import oracle.alura.challenge.forohub.application.dto.request.CreateTopicoRequest;
import oracle.alura.challenge.forohub.application.dto.request.UpdateTopicoRequest;
import oracle.alura.challenge.forohub.application.dto.response.TopicoResponse;
import oracle.alura.challenge.forohub.application.exception.DuplicadoException;
import oracle.alura.challenge.forohub.application.exception.RecursoNoEncontradoException;
import oracle.alura.challenge.forohub.domain.entity.Curso;
import oracle.alura.challenge.forohub.domain.entity.Topico;
import oracle.alura.challenge.forohub.domain.entity.Usuario;
import oracle.alura.challenge.forohub.domain.repository.CursoRepository;
import oracle.alura.challenge.forohub.domain.repository.TopicoRepository;
import oracle.alura.challenge.forohub.domain.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class TopicoServiceImpl implements TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoServiceImpl(
            TopicoRepository topicoRepository,
            UsuarioRepository usuarioRepository,
            CursoRepository cursoRepository
                            ) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public TopicoResponse registrarTopico(CreateTopicoRequest request) {
        // Verificar si el tópico ya existe
        Optional<Topico> topicoExistente = topicoRepository.findByTituloAndMensaje(request.titulo(),
                                                                                   request.mensaje()
                                                                                  );
        if (topicoExistente.isPresent()) {
            throw new DuplicadoException("Ya existe un tópico con el mismo título y mensaje.");
        }

        // Validar que el autor existe
        Usuario autor = usuarioRepository.findById(request.autorId())
                                         .orElseThrow(() -> new RecursoNoEncontradoException("Autor no encontrado."));

        // Validar que el curso existe
        Curso curso = cursoRepository.findById(Long.valueOf(request.cursoId()))
                                     .orElseThrow(() -> new RecursoNoEncontradoException("Curso no encontrado."));

        // Crear el nuevo tópico
        Topico topico = new Topico(request.titulo(),
                                   request.mensaje(),
                                   autor,
                                   curso
        );

        Topico savedTopico = topicoRepository.save(topico);
        return new TopicoResponse(
                savedTopico.getId(),
                savedTopico.getTitulo(),
                savedTopico.getMensaje(),
                savedTopico.getStatus()
                           .name(),
                LocalDateTime.ofInstant(savedTopico.getFechaCreacion(),
                                        ZoneId.systemDefault()
                                       ),
                savedTopico.getAutor()
                           .getNombre(),
                savedTopico.getCurso()
                           .getNombre()
        );
    }
    @Override
    public Page<TopicoResponse> listarTopicos(
            String nombreCurso,
            Integer anio,
            Pageable pageable
                                             ) {
        Page<Topico> topicos = topicoRepository.findByCursoAndAnio(nombreCurso,
                                                                   anio,
                                                                   pageable
                                                                  );
        return topicos.map(topico -> new TopicoResponse(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus()
                      .name(),
                LocalDateTime.ofInstant(topico.getFechaCreacion(),
                                        ZoneId.systemDefault()
                                       ),
                topico.getAutor()
                      .getNombre(),
                topico.getCurso()
                      .getNombre()
        ));
    }

    @Override
    public TopicoResponse obtenerDetalleTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                                        .orElseThrow(() -> new RecursoNoEncontradoException("Tópico con ID " + id + " no encontrado."));

        return new TopicoResponse(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus()
                      .name(),
                LocalDateTime.ofInstant(topico.getFechaCreacion(),
                                        ZoneId.systemDefault()
                                       ),
                topico.getAutor()
                      .getNombre(),
                topico.getCurso()
                      .getNombre()
        );
    }

    @Override
    public void actualizarTopico(
            Long id,
            UpdateTopicoRequest request
                                ) {
        // Verificar si el tópico existe
        Topico topico = topicoRepository.findById(id)
                                        .orElseThrow(() -> new RecursoNoEncontradoException("Tópico con ID " + id + " no encontrado."));

        // Validar si el curso existe
        Curso curso = cursoRepository.findById(request.cursoId())
                                     .orElseThrow(() -> new RecursoNoEncontradoException("Curso con ID " + request.cursoId() + " no encontrado."));

        // Actualizar los campos del tópico
        topico.setTitulo(request.titulo());
        topico.setMensaje(request.mensaje());
        topico.setCurso(curso);

        // Guardar el tópico actualizado
        topicoRepository.save(topico);
    }

    @Override
    public void eliminarTopico(Long id) {
        // Verificar si el tópico existe
        if (!topicoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Tópico con ID " + id + " no encontrado.");
        }

        // Eliminar el tópico
        topicoRepository.deleteById(id);
    }
}