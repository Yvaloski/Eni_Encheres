package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Product;
import fr.eni.encheres.bo.User;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);

    List<Product> getProducts();

    Product getProductById(long id);

    void deleteProduct(long id);

    void updateProduct(Product product);
}
