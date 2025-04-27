package dev.ayelen.courses;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ayelen.videos.Video;

@RestController
@RequestMapping("${api-endpoint}/courses")
public class CourseController {

    CourseService courseService;

    CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> index() {
        List<Course> courses = courseService.getAll();
        return ResponseEntity.ok(courses);
    }

    @GetMapping(path = "/{courseId}")
    public ResponseEntity<Course> show(@PathVariable("courseId") Long courseId) {
        Course course = courseService.getById(courseId);
        return ResponseEntity.status(HttpStatus.OK).body(course);
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course newCourse) {
        Course course = courseService.store(newCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @PutMapping(path = "/{courseId}")
    public ResponseEntity<Course> update(@PathVariable Long courseId, @RequestBody Course updatedCourse) {
        Course course = courseService.update(courseId, updatedCourse);
        return ResponseEntity.ok(course);    
    }

    @DeleteMapping(path = "/{courseId}")
    public ResponseEntity<Void> delete(@PathVariable("courseId") Long courseId) {
        courseService.delete(courseId);
        return ResponseEntity.noContent().build();    
    }

    @PostMapping(path = "/{courseId}/video")
    public ResponseEntity<Video> create(@PathVariable("courseId") Long courseId, @RequestBody Video newVideo) {
        Video video = courseService.storeVideoInCourse(courseId, newVideo);
        return ResponseEntity.status(HttpStatus.CREATED).body(video);
    }

    @DeleteMapping(path = "/{courseId}/video/{videoId}")
    public ResponseEntity<Void> delete(@PathVariable("courseId") Long courseId, @PathVariable("videoId")Long videoId) {
        courseService.deleteVideoFromCourse(courseId, videoId);
        return ResponseEntity.noContent().build();    
    }

}
