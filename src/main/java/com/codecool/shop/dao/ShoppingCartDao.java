package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;


public interface ShoppingCartDao {

    void add(Product product, int num);
    void remove(Product product);

    ShoppingCart getCart();

}
