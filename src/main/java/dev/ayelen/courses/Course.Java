package dev.ayelen.courses;

import dev.ayelen.categories.Category;
import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Course() {}

    public Course(String courseTitle, Category category, String courseDescription, int courseNumVideos, int courseDuration, String courseCover, Float coursePrice) {
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

    public Category getCategory() {
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
}