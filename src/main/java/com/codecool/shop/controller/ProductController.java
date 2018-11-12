package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.ProductCategory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

//        Map params = new HashMap<>();
//        params.put("category", productCategoryDataStore.find(1));
//        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));

        List<String> productCategories = new LinkedList<>();
        for (int i = 0; i < productCategoryDataStore.getAll().size(); i++) {
            productCategories.add(productCategoryDataStore.getAll().get(i).getName());
        }

        String categoryFromURL = req.getParameter("category");

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariables(params);
        context.setVariable("recipient", "World");

        for (int i = 0; i < productCategories.size(); i++) {
            if (categoryFromURL.equals(productCategories.get(i))) {
                context.setVariable("category", productCategoryDataStore.find(i + 1));
                context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(i + 1)));
            } else {
                context.setVariable("category", productCategoryDataStore.find(1));
                context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
            }
        }

        context.setVariable("categories", productCategories);
        engine.process("product/index.html", context, resp.getWriter());

    }

}
