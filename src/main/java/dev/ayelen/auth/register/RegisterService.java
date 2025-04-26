package dev.ayelen.auth.register;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.ayelen.auth.AuthRequest;
import dev.ayelen.auth.register.RegisterExceptions.UserAlreadyExistsException;
import dev.ayelen.roles.Role;
import dev.ayelen.roles.RoleService;
import dev.ayelen.users.User;
import dev.ayelen.users.UserRepository;
import jakarta.transaction.Transactional;

@Service
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

        String password = request.getPassword();

        String passwordEncoded = passwordEncoder.encode(password);

        Role assignedRole = new Role();

        if ("user".equals(request.getRole())) {
            assignedRole = roleService.getUserRole();
        } else if ("admin".equals(request.getRole())) {
            assignedRole = roleService.getAdminRole();
        } else {
            throw new IllegalArgumentException("Rol desconocido: " + request.getRole());
        }
        
        User newUser = new User(
                request.getUsername(),
                passwordEncoded,
                assignedRole);

        userRepository.save(newUser);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Success");
        return response;
    }
}
