package oracle.alura.challenge.forohub.config.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oracle.alura.challenge.forohub.application.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioService usuarioService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            UsuarioService usuarioService
                                  ) {
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
                                   ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        // Verifica si hay un token presente en la cabecera
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request,
                                 response
                                );
            return;
        }
        try {
            // Extraer el token JWT
            final String jwt       = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(jwt);

            // Validar el usuario y el token
            Authentication currentAuth = SecurityContextHolder.getContext()
                                                              .getAuthentication();

            if (userEmail != null && currentAuth == null) {
                UserDetails userDetails = usuarioService.buscarPorCorreo(userEmail)
                                                        .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

                if (jwtService.isTokenValid(jwt,
                                            userDetails
                                           )) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    SecurityContextHolder.getContext()
                                         .setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request,
                                 response
                                );
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter()
                    .write("Token JWT expirado.");
        } catch (SignatureException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter()
                    .write("Firma del JWT no válida.");
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.getWriter()
                    .write("Error interno al validar el JWT.");
        }
    }
}