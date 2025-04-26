package dev.ayelen.auth.login;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ayelen.auth.AuthRequest;

import org.slf4j.Logger;

@RestController
@RequestMapping("${api-endpoint}")
public class LoginController {

     private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final LoginService loginService;
    
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest loginRequest) {
        try {
            LoginResponse response = loginService.authenticate(loginRequest);
            return ResponseEntity.ok(response);
            
        } catch (AuthenticationException ex) {
            logger.error("Error de autenticación: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Credenciales inválidas", null, null));
        }
    }
}

