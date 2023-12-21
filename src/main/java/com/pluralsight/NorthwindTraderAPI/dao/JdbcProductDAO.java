package com.pluralsight.NorthwindTraderAPI.dao;

import com.pluralsight.NorthwindTraderAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcProductDAO implements ProductDAO {
    private DataSource dataSource;
    @Autowired
    public JdbcProductDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products";
        try(Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rows = stmt.executeQuery(query);
            while(rows.next()){
                Product product = new Product();
                product.setProductId(Integer.parseInt(rows.getString("ProductID")));
                product.setProductName(rows.getString("ProductName"));
                product.setUnitPrice(Double.parseDouble(rows.getString("UnitPrice")));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public Product getByID(int id) {
        Product product = null;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM Products WHERE ProductID = ?");
            query.setInt(1, id);
            ResultSet rows = query.executeQuery();

            if (rows.next()) {
                product = new Product();
                product.setProductId(Integer.parseInt(rows.getString("ProductID")));
                product.setProductName(rows.getString("ProductName"));
                product.setUnitPrice(Double.parseDouble(rows.getString("UnitPrice")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
}
