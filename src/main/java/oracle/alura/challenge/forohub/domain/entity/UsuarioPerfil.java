package oracle.alura.challenge.forohub.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(
        name = "usuario_perfil",
        schema = "foro_hub"
)
public class UsuarioPerfil {
    @EmbeddedId
    private UsuarioPerfilId id;

    @MapsId("usuarioId")
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "usuario_id",
            nullable = false
    )
    private Usuario usuario;

    @MapsId("perfilId")
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "perfil_id",
            nullable = false
    )
    private Perfil perfil;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_asignacion")
    private Instant fechaAsignacion;
}
