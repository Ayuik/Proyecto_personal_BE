package dev.ayelen.roles;

import javax.management.relation.RoleNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role getById(Long id) throws RoleNotFoundException {
        Role role = repository.findById(id).orElseThrow( () -> new RoleNotFoundException("Role not found"));
        return role;
    }

    public Role findByRoleName(String roleName) {
        return repository.findByRoleName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found with name: " + roleName));
    }

    public Role getUserRole() {
        return this.findByRoleName("ROLE_USER");
    }

    public Role getAdminRole() {
        return this.findByRoleName("ROLE_ADMIN");
    }

}
