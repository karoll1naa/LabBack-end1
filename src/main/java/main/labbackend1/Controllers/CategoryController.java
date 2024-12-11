package main.labbackend1.Controllers;

import main.labbackend1.Models.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private List<Category> categories = new ArrayList<>();

    @GetMapping
    public List<Category> getAllCategories() {
        return categories;
    }

    @PostMapping
    public void createCategory(@RequestBody Category category) {
        categories.add(category);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        categories.removeIf(category -> category.getId().equals(categoryId));
    }
}
