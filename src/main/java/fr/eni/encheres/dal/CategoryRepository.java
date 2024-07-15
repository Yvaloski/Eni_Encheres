package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findCategoryByLabel(String label);
}
