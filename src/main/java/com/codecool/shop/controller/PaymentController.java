package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
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

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

    ShoppingCartDao sc = ShoppingCartDaoMem.getInstance();
    ProductDao productDataStore = ProductDaoMem.getInstance();

    List products = new ArrayList<Product>();
    List values = new ArrayList<Integer>();
    float totalPrice;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        createTotalPrice();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("sc", sc.getCart().getProducts());
        context.setVariable("total", totalPrice);
        engine.process("product/payment.html", context, resp.getWriter());

    }

    public void createTotalPrice(){
        products = new ArrayList<Product>(sc.getCart().getProducts().keySet());
        values = new ArrayList<Integer>(sc.getCart().getProducts().values());
        totalPrice = 0;
        for(int i=0; i<products.size(); i++){
            for(int j=1; j<=productDataStore.getAll().size(); j++){
                if(products.get(i).equals(productDataStore.find(j))){
                    float price = productDataStore.find(j).getDefaultPrice();
                    int quantity = Integer.valueOf(values.get(i).toString());
                    totalPrice += price * quantity;
                }
            }
        }
    }

}
