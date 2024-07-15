package fr.eni.encheres.controllers;

import fr.eni.encheres.bll.services.CategoryService;
import fr.eni.encheres.bo.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoryController {

    CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public List<Category> getAllCategories() {
         return categoryService.getCategories();
    }

    @GetMapping("/{label}")
    public Category getCategoryByLabel(@PathVariable String label) {
        return categoryService.getCategoryByName(label);
    }
}
