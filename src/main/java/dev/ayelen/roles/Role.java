package dev.ayelen.roles;

import java.util.List;

import dev.ayelen.users.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_role")
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy="role", fetch = FetchType.LAZY)
    private List<User> users;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }
    
    public String getRoleName() {
        return roleName;
    }

    public List<User> getUsers(){
        return users;
    }

}
