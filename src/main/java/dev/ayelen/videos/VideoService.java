package dev.ayelen.videos;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.ayelen.courses.Course;
import dev.ayelen.courses.CourseService;
import jakarta.transaction.Transactional;

@Service
public class VideoService {
    VideoRepository videoRepository;
    CourseService courseService;
    public VideoService(VideoRepository videoRepository, CourseService courseService) {  
        this.videoRepository = videoRepository;
        this.courseService = courseService;
    }

    @Transactional
public Video update(Long videoId, Video updatedData) {
    Video existingVideo = videoRepository.findById(videoId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found"));
    
    existingVideo.setVideoTitle(updatedData.getVideoTitle());
    existingVideo.setVideoUrl(updatedData.getVideoUrl());
    existingVideo.setVideoDescription(updatedData.getVideoDescription());
    existingVideo.setVideoDuration(updatedData.getVideoDuration());
    
    Course course = existingVideo.getVideoCourse();
    course.updateCourseDuration();
    
    return videoRepository.save(existingVideo);
}

     
    public Video getById(Long videoId) {        
        return videoRepository.findById(videoId).orElse(null);    
    }

    public List<Video> getAll() {
        return videoRepository.findAll();    
    }
    
}
