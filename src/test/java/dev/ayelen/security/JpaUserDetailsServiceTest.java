package dev.ayelen.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import dev.ayelen.roles.Role;
import dev.ayelen.users.User;
import dev.ayelen.users.UserRepository;

@ExtendWith(MockitoExtension.class)
public class JpaUserDetailsServiceTest {
    @InjectMocks
    JpaUserDetailsService service;

    @Mock
    UserRepository repository;

    @Test
    void testLoadByUsername(){
        Role role = new Role ("ROLE_USER");
        User user = new User("username", "password", role);
        
        when(repository.findByUsername("username")).thenReturn(Optional.of(user));
        UserDetails returnedUserDetails = service.loadUserByUsername("username");
        
        assertNotNull(returnedUserDetails);
        
        assertEquals("username", returnedUserDetails.getUsername());
        
        assertEquals("password", returnedUserDetails.getPassword());
    }

    @Test
    void testLoadByUsernameNotFound() {
        when(repository.findByUsername("notfound"))
                .thenReturn(Optional.empty());
        
        assertThrows(UsernameNotFoundException.class, () -> {
            service.loadUserByUsername("notfound");
        });
    }
}
