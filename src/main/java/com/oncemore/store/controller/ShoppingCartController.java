package com.oncemore.store.controller;

import com.oncemore.store.dto.CartDTO;
import com.oncemore.store.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;


    @GetMapping("/shoppingCart/addProduct/{productId}")
    @ResponseBody
    public String addProductToCart(@PathVariable("productId") UUID productId, @RequestParam(required = false, defaultValue = "1") int quantity) {
        try {
            shoppingCartService.addProductToCart(productId, quantity);
            return "00";
        } catch (Exception e) {
            return "01";
        }
    }

    @GetMapping("/shoppingCart/view-cart")
    public String viewCart(Model model) {
        CartDTO cartDTO = shoppingCartService.viewCart();
        model.addAttribute("cartDTO", cartDTO);
        return "/user/cart";
    }

    @GetMapping("/shoppingCart/removeProduct/{productId}")
    @ResponseBody
    public String removeProductToCart(@PathVariable("productId") UUID productId) {
        try {
            shoppingCartService.removeProductToCart(productId);
            return "00";
        } catch (Exception e) {
            return "01";
        }
    }

}
