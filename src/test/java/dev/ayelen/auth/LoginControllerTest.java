package dev.ayelen.auth;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.ayelen.auth.login.LoginController;
import dev.ayelen.auth.login.LoginResponse;
import dev.ayelen.auth.login.LoginService;

@WebMvcTest(controllers = LoginController.class, excludeAutoConfiguration = {
        SecurityAutoConfiguration.class,
        SecurityFilterAutoConfiguration.class
})
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    LoginService service;

    @Autowired
    ObjectMapper mapper;

    @Test
    void login() throws JsonProcessingException, Exception {
        AuthRequest loginRequest = new AuthRequest("usuario", "contraseña", null);
        LoginResponse expectedResponse = new LoginResponse("ok", "ey.lkj", "ROLE_USER");

        when(service.authenticate(any(AuthRequest.class))).thenReturn(expectedResponse);

        MockHttpServletResponse servletResponse = mockMvc
                .perform(
                        post("/gonzawhiplash/login")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(loginRequest)))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse();

        assertThat(servletResponse.getStatus(), is(200));
    }

    @Test
    void testLoginAuthenticationException() throws Exception {
        AuthRequest loginRequest = new AuthRequest("usuario", "contraseña", null);

        when(service.authenticate(any(AuthRequest.class)))
                .thenThrow(new BadCredentialsException("Credenciales inválidas"));

        MockHttpServletResponse servletResponse = mockMvc.perform(
                post("/gonzawhiplash/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();

        assertThat(servletResponse.getStatus(), is(401));

    }

}
