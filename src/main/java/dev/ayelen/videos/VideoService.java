package dev.ayelen.videos;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VideoService {
    VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {  
        this.videoRepository = videoRepository;
    }

    public Video update(Long videoId, Video updatedVideoData) {
        Video existingVideo = videoRepository.findById(videoId)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Video not found with id: " + videoId));
        existingVideo.setVideoTitle(updatedVideoData.getVideoTitle());
        existingVideo.setVideoCourse(updatedVideoData.getVideoCourse());
        existingVideo.setVideoDescription(updatedVideoData.getVideoDescription());
        existingVideo.setVideoDuration(updatedVideoData.getVideoDuration());
        existingVideo.setVideoUrl(updatedVideoData.getVideoUrl());
        return videoRepository.save(existingVideo);
    }   

    public Video getById(Long videoId) {        
        return videoRepository.findById(videoId).orElse(null);    
    }

    public List<Video> getAll() {
        return videoRepository.findAll();    
    }
    
}
