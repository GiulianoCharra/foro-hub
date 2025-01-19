package oracle.alura.challenge.forohub.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "topico",
        schema = "foro_hub"
)
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false
    )
    private Integer id;
    @Size(max = 150)
    @NotNull
    @Column(
            name = "titulo",
            nullable = false,
            length = 150
    )
    private String titulo;
    @Size(max = 255)
    @NotNull
    @Column(
            name = "mensaje",
            nullable = false
    )
    private String mensaje;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ABIERTO;
    @NotNull
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "autor_id",
            nullable = false
    )
    private Usuario autor;
    @NotNull
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "curso_id",
            nullable = false
    )
    private Curso curso;
    @Column(
            name = "fecha_creacion",
            nullable = false,
            updatable = false
    )
    private Instant fechaCreacion;
    @Column(name = "fecha_modificacion")
    private Instant fechaModificacion;

    // Constructor con los campos requeridos
    public Topico(
            String titulo,
            String mensaje,
            Usuario autor,
            Curso curso
                 ) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.autor = autor;
        this.curso = curso;
    }
    // Configurar valores antes de persistir la entidad
    @PrePersist
    public void prePersist() {
        this.fechaCreacion = Instant.now();
        this.fechaModificacion = Instant.now();
    }
    @PreUpdate
    public void preUpdate() {
        this.fechaModificacion = Instant.now();
    }

    public enum Status {
        ABIERTO, CERRADO, RESUELTO
    }
}