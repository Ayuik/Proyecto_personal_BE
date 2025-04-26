package dev.ayelen.auth;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.ayelen.auth.register.RegisterController;
import dev.ayelen.auth.register.RegisterExceptions.UserAlreadyExistsException;
import dev.ayelen.auth.register.RegisterService;

@WebMvcTest(controllers = RegisterController.class, excludeAutoConfiguration = {
        SecurityAutoConfiguration.class,
        SecurityFilterAutoConfiguration.class
})
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class RegisterControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    RegisterService service;

    @Autowired
    ObjectMapper mapper;

    @Test
    void register() throws JsonProcessingException, Exception {
        AuthRequest request = new AuthRequest("usuario", "contraseña", null);

        Map<String, String> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "Success");

        when(service.registerUser(any(AuthRequest.class))).thenReturn(expectedResponse);

        MockHttpServletResponse servletResponse = mockMvc
                .perform(
                        post("/gonzawhiplash/register")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse();

        assertThat(servletResponse.getStatus(), is(201));
    }

    @Test
    void testUserAlreadyExistsException() throws Exception {
        AuthRequest request = new AuthRequest("usuario", "contraseña", null);

        when(service.registerUser(any(AuthRequest.class)))
                .thenThrow(new UserAlreadyExistsException("Usuario ya existe"));

        MockHttpServletResponse servletResponse = mockMvc
                .perform(
                        post("/gonzawhiplash/register")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();

        assertThat(servletResponse.getStatus(), is(400));

    }

}
