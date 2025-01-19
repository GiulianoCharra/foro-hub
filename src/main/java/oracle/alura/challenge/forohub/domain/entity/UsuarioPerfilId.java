package oracle.alura.challenge.forohub.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class UsuarioPerfilId implements Serializable {
    @Serial
    private static final long serialVersionUID = -4358914261157361398L;
    @NotNull
    @Column(
            name = "usuario_id",
            nullable = false
    )
    private Integer usuarioId;

    @NotNull
    @Column(
            name = "perfil_id",
            nullable = false
    )
    private Integer perfilId;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsuarioPerfilId entity = (UsuarioPerfilId) o;
        return Objects.equals(this.perfilId,
                              entity.perfilId
                             ) && Objects.equals(this.usuarioId,
                                                 entity.usuarioId
                                                );
    }
    @Override
    public int hashCode() {
        return Objects.hash(perfilId,
                            usuarioId
                           );
    }
}