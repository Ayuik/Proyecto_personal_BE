package dev.ayelen.categories;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category store(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    public Category getById(Long id) {        
        return categoryRepository.findById(id).orElse(null);    
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);    
    }   

    public List<Category> getAll() {
        return categoryRepository.findAll();    
    }
}
