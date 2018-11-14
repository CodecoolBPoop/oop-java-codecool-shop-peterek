package com.codecool.shop.model;

import java.util.HashMap;

public class ShoppingCart {

    private HashMap products = new HashMap();

    public void addProduct(Product product, int num) {
        if (!products.isEmpty() && products.containsKey(product)) {
            int numOfProducts = (int) products.get(product);
            products.put(product, num + numOfProducts);
        } else {
            products.put(product, num);
        }
    }

    public void removeProduct(Product product) {
        if (!products.isEmpty() && products.containsKey(product)) {
            products.remove(product);
        }
    }

    public HashMap getProducts() {
        return products;
    }

}
