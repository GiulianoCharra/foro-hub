package oracle.alura.challenge.forohub.application.controller;

import jakarta.validation.Valid;
import oracle.alura.challenge.forohub.application.dto.request.CreateTopicoRequest;
import oracle.alura.challenge.forohub.application.dto.request.UpdateTopicoRequest;
import oracle.alura.challenge.forohub.application.dto.response.TopicoResponse;
import oracle.alura.challenge.forohub.application.service.TopicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @PostMapping
    public ResponseEntity<TopicoResponse> registrarTopico(@Valid @RequestBody CreateTopicoRequest request) {
        TopicoResponse response = topicoService.registrarTopico(request);
        return new ResponseEntity<>(response,
                                    HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<Page<TopicoResponse>> listarTopicos(
            @RequestParam(required = false) String nombreCurso,
            @RequestParam(required = false) Integer anio,
            @PageableDefault(
                    size = 10,
                    sort = "fechaCreacion"
            ) Pageable pageable
                                                             ) {
        Page<TopicoResponse> topicos = topicoService.listarTopicos(nombreCurso,
                                                                   anio,
                                                                   pageable
                                                                  );
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponse> obtenerDetalleTopico(@PathVariable Long id) {
        TopicoResponse response = topicoService.obtenerDetalleTopico(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarTopico(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTopicoRequest request
                                                ) {
        topicoService.actualizarTopico(id,
                                       request
                                      );
        return ResponseEntity.noContent()
                             .build(); // Respuesta 204 sin contenido
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent()
                             .build(); // Respuesta 204 sin contenido
    }
}