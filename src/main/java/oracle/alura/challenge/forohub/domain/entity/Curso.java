package oracle.alura.challenge.forohub.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "curso",
        schema = "foro_hub"
)
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false
    )
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(
            name = "nombre",
            nullable = false,
            length = 100
    )
    private String nombre;

    @NotNull
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "categoria_id",
            nullable = false
    )
    private Categoria categoria;

    @Column(
            name = "fecha_creacion",
            nullable = false,
            updatable = false
    )
    private Instant fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Instant fechaModificacion;

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
}