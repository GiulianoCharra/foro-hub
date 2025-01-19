package oracle.alura.challenge.forohub.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "usuario",
        schema = "foro_hub"
)
public class Usuario implements UserDetails {
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

    @Size(max = 100)
    @NotNull
    @Column(
            name = "correo_electronico",
            nullable = false,
            length = 100
    )
    private String correoElectronico;

    @Size(max = 255)
    @NotNull
    @Column(
            name = "contrasena",
            nullable = false
    )
    private String contrasena;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_perfil",
            schema = "foro_hub",
            joinColumns = @JoinColumn(
                    name = "usuario_id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "perfil_id",
                    nullable = false
            )
    )
    private Set<Perfil> perfiles = new HashSet<>();

    @Column(
            name = "fecha_creacion",
            nullable = false,
            updatable = false
    )
    private Instant fechaCreacion;
    @Column(name = "fecha_modificacion")
    private Instant fechaModificacion;

    public Usuario(
            String nombre,
            String correoElectronico,
            String contrasena
                  ) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
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
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return perfiles.stream()
//                       .map(perfil -> new SimpleGrantedAuthority(perfil.getNombre()))
//                       .collect(Collectors.toList());
        return List.of();
    }
    @Override
    public String getPassword() {
        return contrasena;
    }
    @Override
    public String getUsername() {
        return correoElectronico;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}