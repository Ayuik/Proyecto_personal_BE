package dev.ayelen.courses;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

    CourseService courseService;    

    CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> index() {
        return courseService.getAll();
    }

    @GetMapping(path = "/{courseId}")
    public ResponseEntity<Course> show(@PathVariable("courseId") Long courseId) {
        Course course = courseService.getById(courseId);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(course);
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course newCourse) {
        Course course = courseService.store(newCourse);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(course);
    }

    @PutMapping(path = "/{courseId}")
    public ResponseEntity<Course> update(@PathVariable Long courseId, @RequestBody Course updatedCourse) {
        Course course = courseService.update(courseId, updatedCourse);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(course);
    }

    @DeleteMapping(path = "/{courseId}")
    public ResponseEntity<Void> delete(@PathVariable("courseId") Long courseId) {
        courseService.delete(courseId);
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }
    
}
