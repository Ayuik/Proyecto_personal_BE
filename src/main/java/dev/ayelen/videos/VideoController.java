package dev.ayelen.videos;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/videos")
public class VideoController {
    VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public List<Video> index() {
        return videoService.getAll();
    }

    @GetMapping(path = "/{videoId}")
    public ResponseEntity<Video> show(@PathVariable("videoId") Long videoId) {
        Video video = videoService.getById(videoId);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(video);
    }

    @PutMapping(path = "/{videoId}")
public ResponseEntity<Video> update(@PathVariable Long videoId, @RequestBody Video updatedVideo) {
    Video video = videoService.update(videoId, updatedVideo);
    return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(video);
}

    
}
