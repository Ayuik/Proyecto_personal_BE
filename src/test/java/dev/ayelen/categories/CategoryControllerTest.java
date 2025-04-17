package dev.ayelen.categories;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(controllers = CategoryController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CategoryControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CategoryService service;

    @Autowired
    ObjectMapper mapper;

    @Test
    void testIndex() throws Exception{
        List<Category> categorys = new ArrayList<>();
        when(service.getAll()).thenReturn(categorys);

        MockHttpServletResponse response = mockMvc
        .perform(
            get("/gonzawhiplash/categories")
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is2xxSuccessful())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus(), is(200));
        
    }

    @Test
    void testShow() throws Exception{
        Category category = new Category();
        when(service.getById(1L)).thenReturn(category);

        MockHttpServletResponse response = mockMvc
        .perform(
            get("/gonzawhiplash/categories/1")
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is2xxSuccessful())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus(), is(200));
        
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(service).delete(1L);
    
        MockHttpServletResponse response = mockMvc
            .perform(delete("/gonzawhiplash/categories/{categoryId}", 1L)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent())
            .andReturn()
            .getResponse();
    
        assertThat(response.getStatus(), is(204));
    
        verify(service).delete(1L);
    }

    @Test
    void testCreate() throws Exception{
        Category category = new Category();
        when(service.store(category)).thenReturn(category);

        String categoryJson = mapper.writeValueAsString(category);

        MockHttpServletResponse response = mockMvc
        .perform(
            post("/gonzawhiplash/categories")
            .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryJson)
        )
        .andExpect(status().is2xxSuccessful())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus(), is(201));
        
    }

    @Test
    void testUpdate() throws Exception{
        Category category = new Category();
        Long categoryId = 1L;
        when(service.update(categoryId, category)).thenReturn(category);

        String categoryJson = mapper.writeValueAsString(category);

        MockHttpServletResponse response = mockMvc
        .perform(
            put("/gonzawhiplash/categories/{categoryId}", categoryId)
            .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryJson)
        )
        .andExpect(status().is2xxSuccessful())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus(), is(200));
        
    }
    
}
