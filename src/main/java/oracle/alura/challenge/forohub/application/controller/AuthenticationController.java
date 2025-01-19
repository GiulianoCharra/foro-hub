package oracle.alura.challenge.forohub.application.controller;

import oracle.alura.challenge.forohub.application.dto.request.LoginUserRequest;
import oracle.alura.challenge.forohub.application.dto.request.RegisterUserRequest;
import oracle.alura.challenge.forohub.application.dto.response.LoginResponse;
import oracle.alura.challenge.forohub.application.service.AuthenticationService;
import oracle.alura.challenge.forohub.config.security.JwtService;
import oracle.alura.challenge.forohub.domain.entity.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(
            JwtService jwtService,
            AuthenticationService authenticationService
                                   ) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Usuario> register(@RequestBody RegisterUserRequest registerUserDto) {

        return ResponseEntity.ok(authenticationService.signup(registerUserDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserRequest loginUserDto) {
        var user  = authenticationService.authenticate(loginUserDto);
        var token = jwtService.generateToken(user);

        LoginResponse loginResponse = new LoginResponse(
                user.getCorreoElectronico(),
                token,
                jwtService.getExpirationTime()
        );

        return ResponseEntity.ok(loginResponse);
    }
}