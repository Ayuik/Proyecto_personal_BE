package dev.ayelen.courses;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.ayelen.videos.Video;

@WebMvcTest(
    controllers = CourseController.class,
    excludeAutoConfiguration = { 
        SecurityAutoConfiguration.class, 
        SecurityFilterAutoConfiguration.class 
    }
)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class CourseControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CourseService service;

    @Autowired
    ObjectMapper mapper;

    @Test
    void testIndex() throws Exception{
        List<Course> courses = new ArrayList<>();
        when(service.getAll()).thenReturn(courses);

        MockHttpServletResponse response = mockMvc
        .perform(
            get("/gonzawhiplash/courses")
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is2xxSuccessful())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus(), is(200));
        
    }

    @Test
    void testShow() throws Exception{
        Course course = new Course();
        when(service.getById(1L)).thenReturn(course);

        MockHttpServletResponse response = mockMvc
        .perform(
            get("/gonzawhiplash/courses/1")
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
            .perform(delete("/gonzawhiplash/courses/{courseId}", 1L)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent())
            .andReturn()
            .getResponse();
    
        assertThat(response.getStatus(), is(204));
    
        verify(service).delete(1L);
    }

    @Test
    void testCreate() throws Exception{
        Course course = new Course();
        when(service.store(course)).thenReturn(course);

        String courseJson = mapper.writeValueAsString(course);

        MockHttpServletResponse response = mockMvc
        .perform(
            post("/gonzawhiplash/courses")
            .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(courseJson)
        )
        .andExpect(status().is2xxSuccessful())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus(), is(201));
        
    }

    @Test
    void testUpdate() throws Exception{
        Course course = new Course();
        Long courseId = 1L;
        when(service.update(courseId, course)).thenReturn(course);

        String courseJson = mapper.writeValueAsString(course);

        MockHttpServletResponse response = mockMvc
        .perform(
            put("/gonzawhiplash/courses/{courseId}", courseId)
            .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(courseJson)
        )
        .andExpect(status().is2xxSuccessful())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus(), is(200));
        
    }

    @Test
    void testDelete_video() throws Exception {
        doNothing().when(service).deleteVideoFromCourse(1L, 1L);
    
        MockHttpServletResponse response = mockMvc
            .perform(delete("/gonzawhiplash/courses/{courseId}/video/{videoId}", 1L, 1L)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent())
            .andReturn()
            .getResponse();
    
        assertThat(response.getStatus(), is(204));
    
        verify(service).deleteVideoFromCourse(1L, 1L);
    }

    @Test
    void testCreate_video() throws Exception{
        Video video = new Video();
        when(service.storeVideoInCourse(1L, video)).thenReturn(video);

        String videoJson = mapper.writeValueAsString(video);

        MockHttpServletResponse response = mockMvc
        .perform(
            post("/gonzawhiplash/courses/{courseId}/video", 1L)
            .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(videoJson)
        )
        .andExpect(status().is2xxSuccessful())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus(), is(201));
        
    }
    

    

}
