package dev.ayelen.courses;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CourseService {
    CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course store(Course course) {
        return courseRepository.save(course);
    }

    public Course update(Course course) {
        return courseRepository.save(course);
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
