package com.isupov.model;

import com.isupov.entities.Product;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Data
@Slf4j
public class Cart {
    private List<CartItem> items;

    private Map<CartItem, Integer> item = new HashMap<CartItem, Integer>();
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(Product product) {
        for (CartItem o : items) {
            if (product.getId().equals(o.getId())) {
                o.changeQuantity(1);
                recalculate();
                return;
            }
        }

        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    public void changeQuantity(String productTitle, Integer delta) {
        for (CartItem o : items) {
            if (o.getProductTitle().equals(productTitle)) {

                o.setQuantity(o.getQuantity() + delta);
                log.debug(String.valueOf(o.getQuantity()));
                if (o.getQuantity() <= 0) {
                    o.setQuantity(1);
                }
                recalculate();
                return;
            }
        }

    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
    }


    public void remove(Long productId) {
        if (items.removeIf(item -> item.getId().equals(productId))) {
            recalculate();
        }
    }

    public void clearCart() {
        items.clear();
        totalPrice = 0;
    }
}
