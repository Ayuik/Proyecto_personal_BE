package dev.ayelen.videos;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = VideoController.class)
@AutoConfigureMockMvc(addFilters = false)
public class VideoControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    VideoService service;

    @Autowired
    ObjectMapper mapper;

    @Test
    void testIndex() throws Exception{
        List<Video> videos = new ArrayList<>();
        when(service.getAll()).thenReturn(videos);

        MockHttpServletResponse response = mockMvc
        .perform(
            get("/gonzawhiplash/videos")
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is2xxSuccessful())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus(), is(200));
        
    }

    @Test
    void testShow() throws Exception{
        Video video = new Video();
        when(service.getById(1L)).thenReturn(video);

        MockHttpServletResponse response = mockMvc
        .perform(
            get("/gonzawhiplash/videos/1")
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is2xxSuccessful())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus(), is(200));
        
    }

    @Test
    void testUpdate() throws Exception{
        Video video = new Video();
        Long videoId = 1L;
        when(service.update(videoId, video)).thenReturn(video);

        String videoJson = mapper.writeValueAsString(video);

        MockHttpServletResponse response = mockMvc
        .perform(
            put("/gonzawhiplash/videos/{videoId}", videoId)
            .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(videoJson)
        )
        .andExpect(status().is2xxSuccessful())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus(), is(200));
        
    }

}
