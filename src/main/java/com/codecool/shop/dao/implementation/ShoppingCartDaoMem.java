package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoMem implements ShoppingCartDao {

    ShoppingCart cart = new ShoppingCart();

    private static ShoppingCartDaoMem instance = null;


    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product, int num) {
        cart.addProduct(product, num);
    }

    @Override
    public void remove(Product product) {
        ShoppingCart cart = getCart();
        cart.removeProduct(product);
    }

    @Override
    public ShoppingCart getCart() {
        return cart;
    }


}
