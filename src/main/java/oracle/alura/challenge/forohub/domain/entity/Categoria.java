package oracle.alura.challenge.forohub.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(
        name = "categoria",
        schema = "foro_hub")
public class Categoria {
    @Id
    @Column(
            name = "id",
            nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(
            name = "nombre",
            nullable = false,
            length = 50)
    private String nombre;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_modificacion")
    private Instant fechaModificacion;
}