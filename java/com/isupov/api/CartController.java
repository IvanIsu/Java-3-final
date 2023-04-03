package com.isupov.api;

import com.isupov.model.Cart;
import com.isupov.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Slf4j
public class CartController {
    private final CartService cartService;

    @GetMapping("add/{id}")
    public Cart addToCart(@PathVariable Long id) {
        cartService.add(id);
        return cartService.getCurrentCart();
    }

    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/change")
    public Cart change(@RequestParam String productTitle, Integer delta) {
        cartService.getCurrentCart().changeQuantity(productTitle, delta);
        return cartService.getCurrentCart();
    }

    @GetMapping("/delete/{id}")
    public Cart deleteProduct(@PathVariable Long id) {
        cartService.remove(id);
        return cartService.getCurrentCart();
    }

    @GetMapping("/clear")
    public Cart clearCart() {
        cartService.clear();
        return cartService.getCurrentCart();
    }


}
