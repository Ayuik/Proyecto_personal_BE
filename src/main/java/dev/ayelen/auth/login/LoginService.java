package dev.ayelen.auth.login;

import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import dev.ayelen.auth.AuthRequest;
import dev.ayelen.security.JwtTokenProvider;
import dev.ayelen.security.SecurityUser;

import org.slf4j.Logger;

@Service
public class LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public LoginService(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    public LoginResponse authenticate(AuthRequest loginRequest) {
        logger.info("Received login request for user: {}", loginRequest.getUsername());
        
        
        if (loginRequest.getPassword() == null || loginRequest.getPassword().trim().isEmpty()) {
            logger.error("Password is null or empty for user: {}", loginRequest.getUsername());
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        
                
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        ); 
        SecurityUser userDetails = (SecurityUser) auth.getPrincipal();
        
        String roleName = userDetails.getAuthorities().stream()
            .findFirst()
            .map(GrantedAuthority::getAuthority)
            .orElse("ROLE_USER");

        logger.info("Authentication successful for user: {} with role: {}", loginRequest.getUsername(), roleName);
        
        String token = tokenProvider.generateToken(auth);
        logger.debug("Generated token: {}", token);
        
        return new LoginResponse("Login exitoso", token, roleName);
    }

    
}
