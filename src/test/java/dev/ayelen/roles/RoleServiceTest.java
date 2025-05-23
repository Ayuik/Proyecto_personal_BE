package dev.ayelen.roles;

import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import javax.management.relation.RoleNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)

public class RoleServiceTest {
    @InjectMocks
    RoleService service;

    @Mock
    RoleRepository rolerepository;

    @BeforeEach
    void setUp(){
        this.service = new RoleService(rolerepository);
    }

    @Test
    void testGetById() throws RoleNotFoundException{
        Role role = new Role ("ROLE_ADMIN");
        ReflectionTestUtils.setField(role, "roleId", 2L);
        when(rolerepository.findById(2L)).thenReturn(Optional.of(role));
        Role returned = service.getById(2L);
        assertThat(returned, is(role));
    }

    @Test
    void testFindByRoleName(){
        Role role = new Role ("ROLE_ADMIN");
        when(rolerepository.findByRoleName(role.getRoleName())).thenReturn(Optional.of(role));
        Role returned = service.findByRoleName("ROLE_ADMIN");
        assertThat(returned, is(role));
        
    }
    
    @Test
    void testGetUserRole(){
        Role role = new Role ("ROLE_USER");
        when(rolerepository.findByRoleName(role.getRoleName())).thenReturn(Optional.of(role));
        Role returned = service.getUserRole();
        assertEquals(role, returned);    
    }

    @Test
    void testGetAdminRol(){
        Role role = new Role ("ROLE_ADMIN");
        when(rolerepository.findByRoleName(role.getRoleName())).thenReturn(Optional.of(role));
        Role returned = service.getAdminRole();
        assertThat(returned, is(role));
        
    }
}
