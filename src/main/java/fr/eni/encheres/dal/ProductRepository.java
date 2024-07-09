package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
