package com.oncemore.store.controller;

import com.oncemore.store.dto.ProductDTO;
import com.oncemore.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @GetMapping("/home")
    public String home(Model model) {
        List<ProductDTO> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product";
    }

}
