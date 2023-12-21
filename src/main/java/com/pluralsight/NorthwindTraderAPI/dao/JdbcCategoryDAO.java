package com.pluralsight.NorthwindTraderAPI.dao;

import com.pluralsight.NorthwindTraderAPI.models.Category;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class JdbcCategoryDAO implements CategoryDAO{
    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public Category getByID(int id) {
        return null;
    }
}
