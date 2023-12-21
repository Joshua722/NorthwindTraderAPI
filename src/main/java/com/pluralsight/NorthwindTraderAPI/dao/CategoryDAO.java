package com.pluralsight.NorthwindTraderAPI.dao;

import com.pluralsight.NorthwindTraderAPI.models.Category;
import com.pluralsight.NorthwindTraderAPI.models.Product;

import java.util.List;

public interface CategoryDAO {
    List<Category> getAll();
    Category getByID(int id);
}
