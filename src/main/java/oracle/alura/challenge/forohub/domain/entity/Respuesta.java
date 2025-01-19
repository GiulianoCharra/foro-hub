package oracle.alura.challenge.forohub.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(
        name = "respuesta",
        schema = "foro_hub"
)
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false
    )
    private Integer id;

    @NotNull
    @Lob
    @Column(
            name = "mensaje",
            nullable = false
    )
    private String mensaje;

    @NotNull
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "topico_id",
            nullable = false
    )
    private Topico topico;

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

    @ColumnDefault("0")
    @Column(name = "solucion")
    private Boolean solucion;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Instant fechaModificacion;

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