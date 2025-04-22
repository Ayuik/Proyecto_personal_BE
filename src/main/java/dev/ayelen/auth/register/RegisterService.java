package dev.ayelen.auth.register;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;

import dev.ayelen.auth.AuthRequest;
import dev.ayelen.auth.register.RegisterExceptions.RegisterException;
import dev.ayelen.auth.register.RegisterExceptions.UserAlreadyExistsException;
import dev.ayelen.roles.Role;
import dev.ayelen.roles.RoleService;
import dev.ayelen.users.User;
import dev.ayelen.users.UserRepository;
import java.util.Base64;
import java.util.Base64.Decoder;
import jakarta.transaction.Transactional;

public class RegisterService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Transactional
    public Map<String, String> registerUser(AuthRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("El usuario ya está registrado");
        }

                String decodedPassword;
        try {
            Decoder decoder = Base64.getDecoder();
            byte[] decodedBytes = decoder.decode(request.getPassword());
            decodedPassword = new String(decodedBytes);
        } catch (Exception e) {
            throw new RegisterException("Error decoding password from Base64", e);
        }

        String passwordEncoded = passwordEncoder.encode(decodedPassword);

        Role defaultRole = roleService.getDefaultRole();

        User newUser = new User(
                request.getUsername(),
                passwordEncoded,
                defaultRole
        );

        userRepository.save(newUser);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Success");
        return response;
    }
}
