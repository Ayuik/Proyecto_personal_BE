package dev.ayelen.courses;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import dev.ayelen.categories.Category;
import dev.ayelen.categories.CategoryRepository;
import dev.ayelen.videos.Video;
import dev.ayelen.videos.VideoRepository;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @InjectMocks
    CourseService service;

    @Mock
    CourseRepository repository;
    @Mock
    CategoryRepository catrepo;
    @Mock
    VideoRepository videorepo;

    @BeforeEach
    void setUp() {
        this.service = new CourseService(repository, catrepo, videorepo);
    };

    @Test
    void testGetAll() {
        List<Course> courses = new ArrayList<>();
        Course newCourse = new Course();
        courses.add(newCourse);
        when(repository.findAll()).thenReturn(courses);
        List<Course> list = service.getAll();
        assertThat(list.size(), is(1));
    }

    @Test
    void testStore() {
        Category providedCategory = new Category();
        ReflectionTestUtils.setField(providedCategory, "categoryId", 1L);
        providedCategory.setCategoryName("Provided Category");

        Category persistentCategory = new Category();
        ReflectionTestUtils.setField(persistentCategory, "categoryId", 1L);
        persistentCategory.setCategoryName("Persistent Category");

        Course newCourse = new Course();
        newCourse.setCourseTitle("Test Course");
        newCourse.setCourseDescription("Test Description");
        newCourse.setCourseCategory(providedCategory);

        when(catrepo.findById(1L)).thenReturn(Optional.of(persistentCategory));

        when(repository.save(newCourse)).thenAnswer(invocation -> {
            Course c = invocation.getArgument(0);
            ReflectionTestUtils.setField(c, "courseId", 1L);
            return c;
        });

        Course storedCourse = service.store(newCourse);

        assertThat(storedCourse.getCourseCategory().getCategoryName(), is("Persistent Category"));
        assertThat(storedCourse.getCourseId(), is(1L));
    }

    @Test
    void testUpdate() {
        Category category = new Category();
        ReflectionTestUtils.setField(category, "categoryId", 1L);
        Course newCourseData = new Course("incoming title", category, "incoming description", "incoming cover", 9F);

        Course persistentCourse = new Course();
        ReflectionTestUtils.setField(persistentCourse, "courseId", 1L);
        persistentCourse.setCourseCategory(category);

        when(catrepo.findById(1L)).thenReturn(Optional.of(category));

        when(repository.save(Mockito.any(Course.class))).thenAnswer(invocation -> {
            Course c = invocation.getArgument(0);
            ReflectionTestUtils.setField(c, "courseId", 1L);
            return c;
        });

        when(repository.findById(1L)).thenReturn(Optional.of(persistentCourse));

        service.store(persistentCourse);

        Long courseId = persistentCourse.getCourseId();
        Course updatedCourse = service.update(courseId, newCourseData);

        assertThat(updatedCourse.getCourseTitle(), is("incoming title"));
    }

    @Test
    void getById() {
        Course course = new Course();
        ReflectionTestUtils.setField(course, "courseId", 2L);
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        when(repository.findById(2L)).thenReturn(Optional.of(course));
        Course returned = service.getById(2L);
        assertThat(returned, is(course));
    }

    @Test
    void delete() {
        Course course = new Course();
        ReflectionTestUtils.setField(course, "courseId", 2L);
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        doNothing().when(repository).deleteById(2L);
        service.delete(2L);
        List<Course> list = service.getAll();
        assertThat(list.size(), is(0));

    }

    @Test
    void storeVideoInCourse() {
        Course course = new Course();
        ReflectionTestUtils.setField(course, "courseId", 1L);
        Video newVideo = new Video();
        newVideo.setVideoTitle("Test Video");
        when(repository.findById(1L)).thenReturn(Optional.of(course));
        when(repository.save(any(Course.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Video savedVideo = service.storeVideoInCourse(1L, newVideo);

        List<Video> videosInCourse = course.getVideos();
        assertThat(videosInCourse.size(), is(1));

        assertThat(savedVideo, is(newVideo));

        assertThat(newVideo.getVideoCourse(), is(course));

    }

    @Test
    void deleteVideoFromCourse() {
        Course course = new Course();
        ReflectionTestUtils.setField(course, "courseId", 1L);
        Video video = new Video();
        ReflectionTestUtils.setField(video, "videoId", 1L);
        course.addVideo(video);
        when(repository.findById(1L)).thenReturn(Optional.of(course));
        when(videorepo.findById(1L)).thenReturn(Optional.of(video));

        service.deleteVideoFromCourse(1L, 1L);

        List<Video> videosInCourse = course.getVideos();
        assertThat(videosInCourse.size(), is(0));

    }

}
