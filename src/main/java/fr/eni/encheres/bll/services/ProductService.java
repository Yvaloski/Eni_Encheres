package fr.eni.encheres.bll.services;

import fr.eni.encheres.bo.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    void addProduct(Product product);

    List<Product> getProducts();

    Product getProductById(long id);

    void deleteProduct(long id);

    Product updateProduct(Product product);

    List<Product> getSalesByUserId(long id);

    List<Map<String, Product>> getOffersByUserId(long id);

    Map<String, Product> getByIdProduct(long id);

    List<Product> getProductsByCategory(String label);

    List<Product> getProductsByName(String name);

}
