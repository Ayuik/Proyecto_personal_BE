package dev.ayelen.roles;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Role not found with id: " + id));
    }

    public Role findByRoleName(String roleName) {
        return repository.findByRoleName(roleName)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Role not found with name: " + roleName));
    }

    public Role getUserRole() {
        return this.findByRoleName("ROLE_USER");
    }

    public Role getAdminRole() {
        return this.findByRoleName("ROLE_ADMIN");
    }

}
