package dev.ayelen.roles;

import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
    
}
