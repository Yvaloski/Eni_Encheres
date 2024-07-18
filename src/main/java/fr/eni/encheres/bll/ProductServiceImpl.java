package fr.eni.encheres.bll;

import fr.eni.encheres.bll.services.ProductService;
import fr.eni.encheres.bo.Category;
import fr.eni.encheres.bo.Product;

import fr.eni.encheres.dal.CategoryRepository;
import fr.eni.encheres.dal.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private final CategoryRepository categoryRepository;
    ProductRepository productRepo;

    public ProductServiceImpl(ProductRepository productRepo, CategoryRepository categoryRepository) {
        this.productRepo = productRepo;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addProduct(Product product) {
        this.validateProduct(product);
        productRepo.save(product);
    }

    private void validateProduct(Product product) {
        if (!product.getSeller().isActive()) {
            throw new RuntimeException("The user is not active, they cannot sell items");
        }
        if(!product.getAuctionEnd().isAfter(product.getAuctionStart())) {
            throw new RuntimeException("The auction end Date must be after start Date");
        }
        if(product.getAuctionStart().plusMonths(1).isAfter(product.getAuctionEnd())) {
            throw new RuntimeException("The auction cannot last for more than one month");
        }
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
    public Product updateProduct(Product product) {
        this.validateProduct(product);
        productRepo.save(product);
        return product;
    }

    @Override
    public List<Product> getSalesByUserId(long id) {
        return productRepo.findBySellerIdUser(id);
    }

    @Override
    public List<Map<String, Product>> getOffersByUserId(long id) {
        return productRepo.findByBidderId(id);
    }

    @Override
    public Map<String, Product> getByIdProduct(long id) {
        return productRepo.findProductById(id);
    }

    @Override
    public List<Product> getProductsByCategory(String label) {
        Category category = categoryRepository.findCategoryByLabel(label).orElse(null);
        if (category == null) {
            return null;
        }
        return productRepo.findProductsByCategory(category);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepo.findProductsByNameProductContaining(name);
    }
}
