package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

    ProductDao productDataStore = ProductDaoMem.getInstance();
    ShoppingCartDao sc = ShoppingCartDaoMem.getInstance();
    String email;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TotalPriceCounter tpc = new TotalPriceCounter();
        float totalPrice = tpc.createFullPrice();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("sc", sc.getCart().getProducts());
        context.setVariable("total", totalPrice);
        engine.process("product/payment.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int Type = Integer.valueOf(req.getParameter("type"));

        if(Type == 1){
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String address = req.getParameter("address");
            String postalCode  = req.getParameter("postalCode");
            String city = req.getParameter("city");
            String country = req.getParameter("country");
            String phoneNumber = req.getParameter("phoneNumber");
            email = req.getParameter("email");
            System.out.println(firstName + "/" + lastName + "/" + address + "/" + postalCode + "/" + city + "/" + country + "/" +phoneNumber + "/" + email );
        } else if (Type == 2){
            String creditCardNumber = req.getParameter("creditCardNumber");
            String expirationDate = req.getParameter("expirationDate");
            String securityCode = req.getParameter("securityCode");
            System.out.println(creditCardNumber + "/" + expirationDate + "/" + securityCode);
            System.out.println(email);

            for(int i = 1; i <= productDataStore.getAll().size(); i++){
                sc.remove(productDataStore.find(i));
            }
            
        }

    }

}
