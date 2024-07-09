package fr.eni.encheres.controllers;

import fr.eni.encheres.bll.ProductService;
import fr.eni.encheres.bo.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }

}
