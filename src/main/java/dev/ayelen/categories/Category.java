package dev.ayelen.categories;

import jakarta.persistence.*;

@Entity
@Table(name="categories")
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category", nullable = false)
    private Long categoryId;
    
    @Column(name = "category_name", nullable = false)
    private String categoryName;

    public Category() {}

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;    
    }

    public void setCategoryName(String categoryNewName) {
        this.categoryName = categoryNewName;
    }
    
}
