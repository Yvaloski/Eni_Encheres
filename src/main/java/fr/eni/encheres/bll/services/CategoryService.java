package fr.eni.encheres.bll.services;

import fr.eni.encheres.bo.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getCategories();
    Optional<Category> getCategoryById(int id);
    Category addCategory(Category category);
    void deleteCategory(int id);
    Category updateCategory(Category category);
}