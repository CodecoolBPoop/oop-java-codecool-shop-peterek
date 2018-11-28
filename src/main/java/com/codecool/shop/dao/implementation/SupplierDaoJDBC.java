package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SupplierDaoJDBC implements SupplierDao {

    private static SupplierDaoJDBC instance = null;

    private SupplierDaoJDBC() {
    }

    public static SupplierDaoJDBC getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJDBC();
        }
        return instance;
    }

    public static int getSupplierIdByName(String name) {
        String query = "SELECT id FROM suppliers WHERE name=?";
        int supplierId = -1;
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                supplierId = result.getInt("id");
            }
            return supplierId;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void add(Supplier supplier) {
        try {
            String query =
                    "INSERT INTO suppliers(name, description) VALUES (?,?)";
            PreparedStatement statement = getConnection().prepareStatement(query);

            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
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
