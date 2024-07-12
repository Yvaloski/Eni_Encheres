package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
