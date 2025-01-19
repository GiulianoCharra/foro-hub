package oracle.alura.challenge.forohub.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(
        name = "perfil",
        schema = "foro_hub"
)
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false
    )
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(
            name = "nombre",
            nullable = false,
            length = 50
    )
    private String nombre;

    @Column(
            name = "fecha_creacion",
            nullable = false,
            updatable = false
    )
    private Instant fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Instant fechaModificacion;
}