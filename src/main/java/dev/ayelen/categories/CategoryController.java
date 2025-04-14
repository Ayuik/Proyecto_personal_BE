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
@RequestMapping("${api-endpoint}/categories")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

       @GetMapping
    public List<Category> index() {
        return categoryService.getAll();
    }

    @GetMapping(path = "/{categoryId}")
    public ResponseEntity<Category> show(@PathVariable("categoryId") Long categoryId) {
        Category category = categoryService.getById(categoryId);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(category);
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category newcategory) {
        Category category = categoryService.store(newcategory);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(category);
    }

    @PutMapping(path = "/{categoryId}")
    public ResponseEntity<Category> update(
            @PathVariable Long categoryId,
            @RequestBody Category updatedCategory) {

        Category category = categoryService.update(categoryId, updatedCategory);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping(path = "/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable("categoryId") Long categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }
    
}
