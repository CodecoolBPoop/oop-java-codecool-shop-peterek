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
import java.util.List;


@WebServlet(urlPatterns = {"/shopping-cart"})
public class ShoppingCartController extends HttpServlet {

    ShoppingCartDao sc = ShoppingCartDaoMem.getInstance();
    ProductDao productDataStore = ProductDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("sc", sc.getCart().getProducts());
        engine.process("product/shopping-cart.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int productIndex = Integer.valueOf(req.getParameter("itemId"));

        List products = new ArrayList<Product>(sc.getCart().getProducts().keySet());
        List values = new ArrayList<Integer>(sc.getCart().getProducts().values());

        for(int i=0; i<products.size(); i++){
            if(products.get(i).equals(productDataStore.find(productIndex))){
                int num = Integer.valueOf(values.get(i).toString());
                if(num > 1){
                    sc.add(productDataStore.find(productIndex), -1);
                } else if (num == 1){
                    sc.remove(productDataStore.find(productIndex));
                }
            }
        }
    }
}

