package com.pluralsight.NorthwindTraderAPI.controllers;

import com.pluralsight.NorthwindTraderAPI.models.Category;
import com.pluralsight.NorthwindTraderAPI.models.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index (
                @RequestParam(defaultValue = "World") String name){
        return "Hello " + name + "!";
    }




}
