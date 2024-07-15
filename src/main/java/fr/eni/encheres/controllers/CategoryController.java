package fr.eni.encheres.controllers;

import fr.eni.encheres.bll.services.CategoryService;
import fr.eni.encheres.bo.Category;
import fr.eni.encheres.dtos.CategoryDto;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
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

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/add")
    public Category addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Category category = new Category();
        category.setLabel(categoryDto.getLabel());
        return categoryService.addCategory(category);
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/{label}/edit")
    public Category editCategory(@PathVariable String label, @Valid @RequestBody CategoryDto categoryDto) {
        Category category = categoryService.getCategoryByName(label);
        category.setLabel(categoryDto.getLabel());
        return categoryService.updateCategory(category);
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/{label}/delete")
    public void deleteCategory(@PathVariable String label) {
        categoryService.deleteCategory(label);

    }


}
