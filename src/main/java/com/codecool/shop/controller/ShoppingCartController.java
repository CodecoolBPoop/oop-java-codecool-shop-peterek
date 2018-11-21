package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
import javassist.compiler.ast.Declarator;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@WebServlet(urlPatterns = {"/shopping-cart"})
public class ShoppingCartController extends HttpServlet {

    ShoppingCartDao sc = ShoppingCartDaoMem.getInstance();
    ProductDao productDataStore = ProductDaoMem.getInstance();

    List products = new ArrayList<Product>();
    List values = new ArrayList<Integer>();
    float fullPrice;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        products = new ArrayList<Product>(sc.getCart().getProducts().keySet());
        values = new ArrayList<Integer>(sc.getCart().getProducts().values());

        createFullPrice();
        System.out.println(fullPrice);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("sc", sc.getCart().getProducts());
        context.setVariable("price", fullPrice);
        engine.process("product/shopping-cart.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int productIndex = Integer.valueOf(req.getParameter("itemId"));
        int type = Integer.valueOf(req.getParameter("type"));

        products = new ArrayList<Product>(sc.getCart().getProducts().keySet());
        values = new ArrayList<Integer>(sc.getCart().getProducts().values());

        if(type == 1){

            for(int i=0; i<products.size(); i++){
                if(products.get(i).equals(productDataStore.find(productIndex))){
                    int num = Integer.valueOf(values.get(i).toString());
                    if(num > 1){
                        sc.add(productDataStore.find(productIndex), -1);
                        System.out.println(productDataStore.find(productIndex).getDefaultPrice() * (num - 1));
                    } else if (num == 1){
                        sc.remove(productDataStore.find(productIndex));
                    }
                }
            }
        } else if (type == 2){
            sc.remove(productDataStore.find(productIndex));
        }

    }

    public void createFullPrice(){
        products = new ArrayList<Product>(sc.getCart().getProducts().keySet());
        values = new ArrayList<Integer>(sc.getCart().getProducts().values());
        fullPrice = 0;
        for(int i=0; i<products.size(); i++){
            for(int j=1; j<=productDataStore.getAll().size(); j++){
                if(products.get(i).equals(productDataStore.find(j))){
                    float price = productDataStore.find(j).getDefaultPrice();
                    int quantity = Integer.valueOf(values.get(i).toString());
                    fullPrice += price * quantity;
                }
            }
        }
    }

}

