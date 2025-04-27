package dev.ayelen.courses;

import java.util.ArrayList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import dev.ayelen.categories.Category;
import dev.ayelen.videos.Video;
import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course", nullable = false)
    private Long courseId;

    @Column(name = "course_title", nullable = false)
    private String courseTitle;

    @ManyToOne
    @JoinColumn(name = "course_category_id", referencedColumnName = "id_category", nullable = false)
    private Category category;

    @Column(name = "course_description", nullable = false)
    private String courseDescription;

    @Column(name = "course_num_videos", nullable = false)
    private int courseNumVideos;

    @Column(name = "course_duration", nullable = false)
    private int courseDuration;

    @Column(name = "course_cover", nullable = false)
    private String courseCover;

    @Column(name = "course_price", nullable = false)
    private Float coursePrice;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Video> videos = new ArrayList<>();

    public Course() {
    }

    public Course(String courseTitle, Category category, String courseDescription, String courseCover, Float coursePrice) {
        this.courseTitle = courseTitle;
        this.category = category;
        this.courseDescription = courseDescription;
        this.courseCover = courseCover;
        this.coursePrice = coursePrice;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public Category getCourseCategory() {
        return category;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public int getCourseNumVideos() {
        return courseNumVideos;
    }

    public int getCourseDuration() {
        return courseDuration;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public Float getCoursePrice() {
        return coursePrice;
    }

    @JsonManagedReference
    public List<Video> getVideos() {
        return videos;
    }   

    public void setCoursePrice(Float coursePrice) {
        this.coursePrice = coursePrice;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
    }

    public void updateCourseDuration() {
        int totalDuration = 0;
        for (Video video : videos) {
            totalDuration += video.getVideoDuration();
        }
        this.courseDuration = totalDuration;    
    }

    public void updateCourseNumVideos() {
        this.courseNumVideos = videos.size();
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public void setCourseCategory(Category courseCategory) {
        this.category = courseCategory;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void addVideo(Video video) {
        videos.add(video);
        video.setVideoCourse(this);
        updateCourseDuration();
        updateCourseNumVideos();
    }
    
    public void removeVideo(Video video) {
        videos.remove(video);
        updateCourseDuration();
        updateCourseNumVideos();
    }
    
}