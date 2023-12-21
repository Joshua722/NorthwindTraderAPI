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

        try (Connection conn = dataSource.getConnection();
            PreparedStatement query = conn.prepareStatement("SELECT * FROM Products WHERE ProductID = ?")) {
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

    @Override
    public Product insert(Product product) {

       try(Connection conn = dataSource.getConnection();
           PreparedStatement query = conn.prepareStatement("INSERT INTO Products (ProductName, UnitPrice) VALUES (?, ?)")){
           query.setString(1, product.getProductName());
           query.setDouble(2,product.getUnitPrice());
           int rows = query.executeUpdate();
           if (rows > 0) {
               System.out.println("Product has been added.");
               return product;
           } else {
               System.out.println("Something went wrong, failed to add.");
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return product;
    }

    @Override
    public void delete(int id) {
        try(Connection conn = dataSource.getConnection();
            PreparedStatement deleteEx = conn.prepareStatement("DELETE FROM Products WHERE ProductID = ?")){
            deleteEx.setInt(1,id);
            int rowsDeleted = deleteEx.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Delete was Successful");
            }
            else{
                System.out.println("Invalid ProductID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
