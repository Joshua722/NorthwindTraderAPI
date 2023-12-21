package com.pluralsight.NorthwindTraderAPI.controllers;

import com.pluralsight.NorthwindTraderAPI.models.Category;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

public class CategoriesController {
    @GetMapping("/categories")
    public List<Category> getCategories(){
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(2,"Dairy"));
        categories.add(new Category(1,"Meat"));
        return categories;
    }
}
