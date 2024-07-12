package fr.eni.encheres.controllers;

import fr.eni.encheres.bll.services.ProductService;
import fr.eni.encheres.bll.services.UserService;
import fr.eni.encheres.bo.Category;
import fr.eni.encheres.bo.Product;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;
    UserService userService;

    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/")
    public List<Product> getProducts() {
        System.out.println(productService.getProducts());
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setNameProduct(productDto.getNameProduct());
        product.setDescriptionProduct(productDto.getDescriptionProduct());
        product.setAuctionStart(productDto.getAuctionStart());
        product.setAuctionEnd(productDto.getAuctionEnd());
        product.setStartPrice(productDto.getStartPrice());
        product.setFinalPrice(productDto.getFinalPrice());

        User seller = userService.getUserById(productDto.getSellerId());
        product.setSeller(seller);

//        Category category = productService. productDto.getCategoryId();
//        category.setIdCat(productDto.getCategoryId());
//        product.setCategory(productDto.getCategoryId());

        product.setSaleState(productDto.getSaleState());
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
