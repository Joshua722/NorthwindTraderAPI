package com.pluralsight.NorthwindTraderAPI.dao;

import com.pluralsight.NorthwindTraderAPI.models.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getAll();
    Product getByID(int id);
    Product insert(Product product);
    void delete(int id);
}
