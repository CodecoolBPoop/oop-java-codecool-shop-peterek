package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {

    private static ProductCategoryDaoJDBC instance = null;

    private ProductCategoryDaoJDBC() {
    }

    public static ProductCategoryDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJDBC();
        }
        return instance;
    }

    public static int getIdByName(String name) {
        String getName = "SELECT id FROM category WHERE name=?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement getItByName = connection.prepareStatement(getName)
        ) {
            getItByName.setString(1, name);
            ResultSet resultSet = getItByName.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void add(ProductCategory category) {
        try {
            String query =
                    "INSERT INTO category(name, department, description) VALUES (?,?,?)";
            PreparedStatement statement = getConnection().prepareStatement(query);

            statement.setString(1, category.getName());
            statement.setString(2, category.getDepartment());
            statement.setString(3, category.getDescription());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductCategory find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
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
