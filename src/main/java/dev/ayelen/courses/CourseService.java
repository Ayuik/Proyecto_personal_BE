package dev.ayelen.courses;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.ayelen.categories.Category;
import dev.ayelen.categories.CategoryRepository;

@Service
public class CourseService {
    CourseRepository courseRepository;
    CategoryRepository categoryRepository;

    public CourseService(CourseRepository courseRepository, CategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
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
        existingCourse.setCourseNumVideos(updatedCourseData.getCourseNumVideos());
        existingCourse.setCourseDuration(updatedCourseData.getCourseDuration());
        existingCourse.setCourseCover(updatedCourseData.getCourseCover());
        existingCourse.setCoursePrice(updatedCourseData.getCoursePrice());

        return courseRepository.save(existingCourse);
    }

    public Course getById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

}
