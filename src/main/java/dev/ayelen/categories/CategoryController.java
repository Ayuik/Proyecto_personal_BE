package dev.ayelen.categories;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

       @GetMapping
    public List<Category> index() {
        return categoryService.getAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Category> show(@PathVariable("id") Long categoryId) {
        Category category = categoryService.getById(categoryId);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(category);
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category newcategory) {
        Category category = categoryService.store(newcategory);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(category);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Category> update(@RequestBody Category updatedcategory) {
        Category category = categoryService.update(updatedcategory);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(category);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }
    
}
