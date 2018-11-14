package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import java.io.BufferedReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        ShoppingCartDao sc = ShoppingCartDaoMem.getInstance();
        sc.add(productDataStore.getAll().get(1), 2);


//        Map params = new HashMap<>();
//        params.put("category", productCategoryDataStore.find(1));
//        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));

        List<String> productCategories = new LinkedList<>();
        for (int i = 0; i < productCategoryDataStore.getAll().size(); i++) {
            productCategories.add(productCategoryDataStore.getAll().get(i).getName());
        }

        String categoryFromURL = req.getParameter("category");
        String supplierFromURL = req.getParameter("supplier");

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariables(params);
        context.setVariable("recipient", "World");

        if (categoryFromURL != null) {
            for (int i = 0; i < productCategories.size(); i++) {
                if (categoryFromURL.equals(productCategories.get(i))) {
                    context.setVariable("category", productCategoryDataStore.find(i + 1));
                    context.setVariable("categoryName", productCategoryDataStore.find(i + 1).getName());
                    context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(i + 1)));
                    context.setVariable("supplier", supplierDataStore.getAll());
                    context.setVariable("supplierName", "Choose");
                }
            }
        } else if (supplierFromURL != null) {
            for (int i = 0; i < supplierDataStore.getAll().size(); i++) {
                if (supplierFromURL.equals(supplierDataStore.getAll().get(i).getName())) {
                    context.setVariable("category", productCategoryDataStore.find(i + 1));
                    context.setVariable("categoryName", "Choose");
                    context.setVariable("products", productDataStore.getBy(supplierDataStore.find(i + 1)));
                    context.setVariable("supplier", supplierDataStore.getAll());
                    context.setVariable("supplierName", supplierDataStore.getAll().get(i).getName());
                }
            }
        } else {
            context.setVariable("category", productCategoryDataStore.find(1));
            context.setVariable("categoryName", productCategoryDataStore.find(1).getName());
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
            context.setVariable("supplier", supplierDataStore.getAll());
            context.setVariable("supplierName", "Choose");
        }

        context.setVariable("categories", productCategories);
        engine.process("product/index.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuffer jsonData = new StringBuffer();
        String line;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jsonData.append(line);
        } catch (Exception e) {
            System.out.println("Error");
        }

        System.out.println(jsonData);
    }
}
