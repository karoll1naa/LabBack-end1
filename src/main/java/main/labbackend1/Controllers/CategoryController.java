package main.labbackend1.Controllers;

import main.labbackend1.Models.Category;
import main.labbackend1.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/global")
    public List<Category> getGlobalCategories() {
        return categoryRepository.findByIsGlobalTrue();
    }

    @GetMapping("/{userId}/categories")
    public List<Category> getUserCategories(@PathVariable Long userId) {
        return categoryRepository.findByIsGlobalFalseAndUserId(userId);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        // Ручна валідація
        if (category.getName() == null || category.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Category name cannot be empty");
        }

        // Збереження категорії
        Category savedCategory = categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }
    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createCategoryForUser(@PathVariable Long userId, @RequestBody Category category) {
        // Перевірка чи існує користувач з таким ID
        if (!categoryRepository.existsById(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + userId + " not found");
        }

        // Прив'язка категорії до користувача
        category.setUserId(userId);

        // Збереження категорії
        Category savedCategory = categoryRepository.save(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Category with ID " + categoryId + " not found");
        }

        categoryRepository.deleteById(categoryId);
        return ResponseEntity.noContent().build();
    }
}
