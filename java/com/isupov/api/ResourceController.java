package com.isupov.api;

import com.isupov.entities.Product;
import com.isupov.exception.ResourceNotFoundException;
import com.isupov.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ResourceController {

    private final ProductService productService;


    @GetMapping()
    public List<Product> findALlProducts() {
        return productService.findAll();
    }




    @GetMapping("/{id}")
    public Product findProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found ID: " + id));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductsById(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
