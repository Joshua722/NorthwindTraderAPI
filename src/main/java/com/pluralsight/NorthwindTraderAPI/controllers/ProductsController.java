package com.pluralsight.NorthwindTraderAPI.controllers;

import com.pluralsight.NorthwindTraderAPI.dao.JdbcProductDAO;
import com.pluralsight.NorthwindTraderAPI.dao.ProductDAO;
import com.pluralsight.NorthwindTraderAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {
    private ProductDAO productDAO;

    @Autowired
    public ProductsController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @RequestMapping(path = "/products/",
            method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return productDAO.getAll();
    }

    @RequestMapping(path = "/products/{id}",
            method = RequestMethod.GET)
    public Product getProductById(@PathVariable int id) {
        return productDAO.getByID(id);
    }
    @RequestMapping(path = "/products/insert",
            method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Product insertNew(@RequestBody Product product){
        return productDAO.insert(product);
    }
    @RequestMapping(path = "products/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id){
        productDAO.delete(id);
    }
}
