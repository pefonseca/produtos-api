package com.pefonseca.produtosapi.controller;

import com.pefonseca.produtosapi.model.Product;
import com.pefonseca.produtosapi.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable(name = "id") String id) {
        return productRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        var id = UUID.randomUUID().toString();
        product.setId(id);
        productRepository.save(product);
        System.out.println("Product Recebido: " + product);
        return product;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable(name = "id") String id) {
        productRepository.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public void updateProduct(@PathVariable(name = "id") String id,
                              @RequestBody Product product) {
        product.setId(id);
        productRepository.save(product);
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam("name") String name) {
        return productRepository.findByName(name);
    }
}