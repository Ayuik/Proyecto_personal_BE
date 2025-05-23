package dev.ayelen.courses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.ayelen.categories.Category;
import dev.ayelen.categories.CategoryRepository;
import dev.ayelen.videos.Video;
import dev.ayelen.videos.VideoRepository;
import jakarta.transaction.Transactional;

@Service
public class CourseService {
    CourseRepository courseRepository;
    CategoryRepository categoryRepository;
    VideoRepository videoRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, CategoryRepository categoryRepository,
            VideoRepository videoRepository) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
        this.videoRepository = videoRepository;
    }

    public Course store(Course course) {
        Category category = categoryRepository.findById(course.getCourseCategory().getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        course.setCourseCategory(category);
        return courseRepository.save(course);
    }

    public Course update(Long courseId, Course updatedCourseData) {
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course not found with id: " + courseId));
        existingCourse.setCourseTitle(updatedCourseData.getCourseTitle());
        Category updatedCategory = categoryRepository
                .findById(updatedCourseData.getCourseCategory().getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        existingCourse.setCourseCategory(updatedCategory);
        existingCourse.setCourseDescription(updatedCourseData.getCourseDescription());
        existingCourse.setCourseCover(updatedCourseData.getCourseCover());
        existingCourse.setCoursePrice(updatedCourseData.getCoursePrice());

        return courseRepository.save(existingCourse);
    }

    public Course getById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
    }

    public void delete(Long courseId) {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Course not found with id: " + courseId));
        courseRepository.delete(course);
    }
    
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Transactional
    public Video storeVideoInCourse(Long courseId, Video newVideo) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
        course.addVideo(newVideo);
        courseRepository.save(course);
        return newVideo;
    }

    @Transactional
    public void deleteVideoFromCourse(Long courseId, Long videoId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found"));
        course.removeVideo(video);
        courseRepository.save(course);
    }
}
