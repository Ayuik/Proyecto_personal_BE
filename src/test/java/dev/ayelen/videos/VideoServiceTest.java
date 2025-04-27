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

import dev.ayelen.courses.Course;
import dev.ayelen.courses.CourseService;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {
    @InjectMocks
    VideoService service;

    @Mock
    VideoRepository repository;

    @Mock
    CourseService courseService;

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
        persistentVideo.setVideoDuration(10);
        
        Course course = new Course();
        course.addVideo(persistentVideo);
        ReflectionTestUtils.setField(course, "courseId", 1L);

        Video newVideoData = new Video();
        newVideoData.setVideoTitle("incoming title");
        newVideoData.setVideoDuration(5);

        when(repository.findById(1L)).thenReturn(Optional.of(persistentVideo));
        when(repository.save(Mockito.any(Video.class))).thenAnswer(invocation -> {
            Video v = invocation.getArgument(0);
            ReflectionTestUtils.setField(v, "videoId", 1L);
            return v;
        });

        course.updateCourseDuration();

        Long videoId = persistentVideo.getVideoId();
        persistentVideo = service.update(videoId, newVideoData);

        assertThat(persistentVideo.getVideoTitle(), is("incoming title"));
        assertThat(course.getCourseDuration(), is(persistentVideo.getVideoDuration()));
    }

}
