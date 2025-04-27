package dev.ayelen.categories;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category store(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Long categoryId, Category updatedCategoryData) {
        Category existingCategory = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Category not found with id: " + categoryId));
        existingCategory.setCategoryName(updatedCategoryData.getCategoryName());
        return categoryRepository.save(existingCategory);
    }   

    public Category getById(Long categoryId) {
        return categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Category not found with id: " + categoryId));
    }  

    public void delete(Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Category not found with id: " + categoryId));
        categoryRepository.delete(existingCategory);
    }
      
    public List<Category> getAll() {
        return categoryRepository.findAll();    
    }
}
