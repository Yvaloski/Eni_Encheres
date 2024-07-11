package fr.eni.encheres.controllers;

import fr.eni.encheres.bll.services.ProductService;
import fr.eni.encheres.bo.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return product;
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return "Product deleted";
    }

    @PostMapping("/{id}/edit")
    public String editProduct(@PathVariable long id, @RequestBody Product product) {
        productService.updateProduct(product);
        return "Product edited";
    }

}
