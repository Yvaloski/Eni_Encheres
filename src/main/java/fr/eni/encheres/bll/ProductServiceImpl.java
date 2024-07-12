package fr.eni.encheres.bll;

import fr.eni.encheres.bll.services.ProductService;
import fr.eni.encheres.bo.Product;

import fr.eni.encheres.dal.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepo;

    public ProductServiceImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public void addProduct(Product product) {
        productRepo.save(product);
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepo.findAll();
        System.out.println(products);
        return products;
    }

    @Override
    public Product getProductById(long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteProduct(long id) {
        productRepo.deleteById(id);
    }

    @Override
    public void updateProduct(Product product) {
        productRepo.save(product);
    }
}
