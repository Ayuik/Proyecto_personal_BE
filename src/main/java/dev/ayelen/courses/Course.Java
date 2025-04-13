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

    public Course(String courseTitle, Category category, String courseDescription, int courseNumVideos,
            int courseDuration, String courseCover, Float coursePrice) {
        this.courseTitle = courseTitle;
        this.category = category;
        this.courseDescription = courseDescription;
        this.courseNumVideos = courseNumVideos;
        this.courseDuration = courseDuration;
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

    public void setCoursePrice(Float courseNewPrice) {
        this.coursePrice = courseNewPrice;
    }

    public void setCourseCover(String courseNewCover) {
        this.courseCover = courseNewCover;
    }

    public void setCourseDuration(int courseNewDuration) {
        this.courseDuration = courseNewDuration;
    }

    public void setCourseNumVideos(int courseNewNumVideos) {
        this.courseNumVideos = courseNewNumVideos;
    }

    public void setCourseDescription(String courseNewDescription) {
        this.courseDescription = courseNewDescription;
    }

    public void setCourseCategory(Category courseNewCategory) {
        this.category = courseNewCategory;
    }

    public void setCourseTitle(String courseNewTitle) {
        this.courseTitle = courseNewTitle;
    }

    public void addVideo(Video video) {
        videos.add(video);
        video.setVideoCourse(this);
    }
    
    public void removeVideo(Video video) {
        videos.remove(video);
        video.setVideoCourse(null);
    }
    
}