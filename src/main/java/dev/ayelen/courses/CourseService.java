package dev.ayelen.courses;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CourseService {
    CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course store(Course course) {
        return courseRepository.save(course);
    }

    public Course update(Long courseId, Course updatedCourseData) {
        Course existingCourse = courseRepository.findById(courseId)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Course not found with id: " + courseId));
        existingCourse.setCouseTitle(updatedCourseData.getCourseTitle());
        existingCourse.setCourseCategory(updatedCourseData.getCourseCategory());
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
