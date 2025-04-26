package dev.ayelen.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.ayelen.roles.Role;
import dev.ayelen.users.User;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class SecurityUserTest {

    User user;
    SecurityUser securityUser;
    Role role;

    @BeforeEach
    void setUp(){
        role = new Role ("ROLE_USER");
        user = new User ("mockUsername", "mockPassword", role);
        securityUser = new SecurityUser(user);
    }

    @Test
    void testGetUsername(){
        String username = securityUser.getUsername();
        assertEquals(username, "mockUsername");
    }

    @Test
    void testGetPassword(){
        String password = securityUser.getPassword();
        assertEquals (password, "mockPassword");
    }

    @Test
    void testGetAuthorities(){
        Collection<? extends GrantedAuthority> authorities = securityUser.getAuthorities();
        assertNotNull(authorities, "La colección de autoridades no debería ser null");
        assertEquals(1, authorities.size(), "Debería haber exactamente 1 autoridad");

        GrantedAuthority authority = authorities.iterator().next();
        SimpleGrantedAuthority expectedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        assertEquals(expectedAuthority.getAuthority(), authority.getAuthority(), 
                "La autoridad debe coincidir con ROLE_USER");    }
    
}
