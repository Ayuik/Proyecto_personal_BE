package dev.ayelen.videos;

import static org.mockito.Mockito.when;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {
    @InjectMocks
    VideoService service;

    @Mock
    VideoRepository repository;

    @Test
    void testGetAll() {
        List<Video> videos = new ArrayList<>();
        Video video = new Video();
        videos.add(video);
        when(repository.findAll()).thenReturn(videos);
        List<Video> list = service.getAll();
        assertThat(list.size(), is(1));
    }

    @Test
    void getById() {
        Video video = new Video();
        ReflectionTestUtils.setField(video, "videoId", 1L);
        List<Video> videos = new ArrayList<>();
        videos.add(video);
        when(repository.findById(1L)).thenReturn(Optional.of(video));
        Video returned = service.getById(1L);
        assertThat(returned, is(video));
    }

    @Test
    void testUpdate() {
        Video persistentVideo = new Video();
        ReflectionTestUtils.setField(persistentVideo, "videoId", 1L);
        persistentVideo.setVideoTitle("persistent title");

        Video newVideoData = new Video();
        newVideoData.setVideoTitle("incoming title");

        when(repository.findById(1L)).thenReturn(Optional.of(persistentVideo));
        when(repository.save(Mockito.any(Video.class))).thenAnswer(invocation -> {
            Video v = invocation.getArgument(0);
            ReflectionTestUtils.setField(v, "videoId", 1L);
            return v;
        });

        Long videoId = persistentVideo.getVideoId();
        persistentVideo = service.update(videoId, newVideoData);

        assertThat(persistentVideo.getVideoTitle(), is("incoming title"));
    }

}
