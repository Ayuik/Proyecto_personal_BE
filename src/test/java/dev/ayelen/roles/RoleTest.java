package dev.ayelen.roles;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import dev.ayelen.users.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class RoleTest {
    @Test
    void testGetters() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Role role = new Role("MOCK_ROLE");
        ReflectionTestUtils.setField(role, "roleId", 3L);
        User user = new User("mockuser", "mockpassword", role);
        List<User> users = new ArrayList<>();
        users.add(user);

        Field usersField = Role.class.getDeclaredField("users");
        usersField.setAccessible(true);
        usersField.set(role, users);

        assertThat(role.getRoleName(), is("MOCK_ROLE"));
        assertThat(role.getUsers().size(), is(1));
    }

}
