package dev.ayelen.videos;

import com.fasterxml.jackson.annotation.JsonBackReference;

import dev.ayelen.courses.Course;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "videos")
public class Video {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_video", nullable = false)
    private Long videoId;

    @Column(name = "video_title", nullable = false)
    private String videoTitle;

    @Column(name = "video_url", nullable = false)
    private String videoUrl;

    @ManyToOne(targetEntity = Course.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "video_course_id", referencedColumnName = "id_course", nullable = false)
    private Course course;

    @Column(name = "video_description", nullable = false)
    private String videoDescription;

    @Column(name = "video_duration", nullable = false)
    private int videoDuration;

    public Video() {}

    public Video(String videoTitle, String videoUrl, Course course, String videoDescription, int videoDuration) {
        this.videoTitle = videoTitle;
        this.videoUrl = videoUrl;
        this.course = course;
        this.videoDescription = videoDescription;
        this.videoDuration = videoDuration;
    }

    public Long getVideoId() {
        return videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }   

    public String getVideoUrl() {
        return videoUrl;
    }

    @JsonBackReference
    public Course getVideoCourse() {
        return course;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public int getVideoDuration() {
        return videoDuration;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public void setVideoUrl(String videoUrl) {    
        this.videoUrl = videoUrl;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }   

    public void setVideoDuration(int videoDuration) {
        this.videoDuration = videoDuration;
    }

    public void setVideoCourse(Course videoCourse) {
        this.course = videoCourse;
    }       

}