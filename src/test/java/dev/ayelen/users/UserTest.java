package dev.ayelen.users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import dev.ayelen.roles.Role;

public class UserTest {
    User user;
    Role role;

    @BeforeEach
    void setUp(){
        role = new Role ("ROLE_USE");
        user = new User ("ayeuser", "ayepassword", role);
        ReflectionTestUtils.setField(user, "userId", 1L);

    }

    @Test
    void testGetters(){
        assertEquals(user.getUserId(), 1L);
        assertEquals(user.getUsername(), "ayeuser");
        assertEquals(user.getPassword(), "ayepassword");
        assertEquals(user.getRole(), role);
    }
    
}
