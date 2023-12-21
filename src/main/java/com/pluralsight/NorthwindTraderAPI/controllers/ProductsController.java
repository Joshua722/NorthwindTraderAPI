package com.pluralsight.NorthwindTraderAPI.controllers;

import com.pluralsight.NorthwindTraderAPI.dao.ProductDAO;
import com.pluralsight.NorthwindTraderAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {
    private ProductDAO productDAO;

    @Autowired
    public ProductsController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @RequestMapping(path="/products/",
                    method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        var products = productDAO.getAll();
        return products;
    }

    @RequestMapping(path="/products/{id}",
                    method = RequestMethod.GET)
    public Product getProductById(@PathVariable int id) {
        Product product = productDAO.getByID(id);
        return product;
    }
}
