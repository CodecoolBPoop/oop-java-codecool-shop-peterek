package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class TotalPriceCounter {

    ShoppingCartDao sc = ShoppingCartDaoMem.getInstance();
    ProductDao productDataStore = ProductDaoMem.getInstance();

    List products = new ArrayList<Product>();
    List values = new ArrayList<Integer>();

    public float createFullPrice(){
        products = new ArrayList<Product>(sc.getCart().getProducts().keySet());
        values = new ArrayList<Integer>(sc.getCart().getProducts().values());
        float totalPrice = 0;
        for(int i=0; i<products.size(); i++){
            for(int j=1; j<=productDataStore.getAll().size(); j++){
                if(products.get(i).equals(productDataStore.find(j))){
                    float price = productDataStore.find(j).getDefaultPrice();
                    int quantity = Integer.valueOf(values.get(i).toString());
                    totalPrice += price * quantity;
                }
            }
        }
        return totalPrice;
    }

}
