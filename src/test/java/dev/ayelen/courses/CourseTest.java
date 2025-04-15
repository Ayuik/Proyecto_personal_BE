package dev.ayelen.courses;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;

import dev.ayelen.categories.Category;
import dev.ayelen.videos.Video;

public class CourseTest {

    Category mockCategory;
    Course course;
    Video mockVideo;

    @BeforeEach
    void setUp() {
        mockCategory = new Category("mock category name");
        mockVideo = new Video("mock video title", "mockvideourl", course, "mock video description", 35);
        course = new Course("mock title", mockCategory, "mock description", "/mockimage", 19F);
    }

    @DisplayName("Should create a course")
    @Test
    void testCourse() {
        assertEquals(course.getCourseTitle(), "mock title");
        assertEquals(course.getCourseCategory(), mockCategory);
        assertEquals(course.getCourseDescription(), "mock description");
        assertEquals(course.getCourseCover(), "/mockimage");
        assertEquals(course.getCoursePrice(), 19F);
    }

    @DisplayName("Should add a video to the list")
    @Test
    void testAddVideo() {
        course.addVideo(mockVideo);
        assertThat(course.getVideos().size(), is(1));
        assertThat(course.getCourseNumVideos(), is(1));
        assertThat(course.getCourseDuration(), is(35));
    }

    @DisplayName("Should remove a video from the list")
    @Test
    void testRemoveVideo() {
        course.removeVideo(mockVideo);
        assertThat(course.getVideos().size(), is(0));
        assertThat(course.getCourseNumVideos(), is(0));
        assertThat(course.getCourseDuration(), is(0));
    }

    @DisplayName("Should assign new values")
    @Test
    void testSetter() {
        Category newCategory = new Category("new category");
        course.setCourseTitle("new title");
        course.setCourseCategory(newCategory);
        course.setCourseDescription("new description");
        course.setCourseCover("/newimage");
        course.setCoursePrice(91F);
        assertEquals(course.getCourseTitle(), "new title");
        assertEquals(course.getCourseCategory(), newCategory);
        assertEquals(course.getCourseDescription(), "new description");
        assertEquals(course.getCourseCover(), "/newimage");
        assertEquals(course.getCoursePrice(), 91F);
    }

}
