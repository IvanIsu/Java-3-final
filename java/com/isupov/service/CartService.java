package com.isupov.service;

import com.isupov.exception.ResourceNotFoundException;
import com.isupov.model.Cart;
import com.isupov.entities.Product;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private Cart tempCart;

    private final ProductService productService;


    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }


    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        Product product = productService.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Не удаеться добавить продукт с id:" + productId + "в карзину. Продукт не найден."));
        tempCart.add(product);
    }

    public void remove(Long productId) {
        tempCart.remove(productId);
    }

    public void clear() {
        tempCart.clearCart();
    }
}
