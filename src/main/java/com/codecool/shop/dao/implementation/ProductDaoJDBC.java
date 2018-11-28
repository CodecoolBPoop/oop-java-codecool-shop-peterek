package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoJDBC implements ProductDao {

    private static ProductDaoJDBC instance = null;

    private ProductDaoJDBC() {
    }

    public static ProductDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        try {
            String query =
                    "INSERT INTO items(name, defaultprice, currencystring, description, category, supplier) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = getConnection().prepareStatement(query);

            int productCategoryId = ProductCategoryDaoJDBC.getIdByName(product.getProductCategory().getName());
            int supplierId = SupplierDaoJDBC.getSupplierIdByName(product.getSupplier().getName());

            statement.setString(1, product.getName());
            statement.setFloat(2, product.getDefaultPrice());
            statement.setString(3, String.valueOf(product.getDefaultCurrency()));
            statement.setString(4, product.getDescription());
            statement.setInt(5, productCategoryId);
            statement.setInt(6, supplierId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }

    private Connection getConnection(){
        try {
            return DbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception thrown");
        }
        return null;
    }
}
