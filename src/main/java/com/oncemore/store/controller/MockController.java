package com.oncemore.store.controller;

import com.github.javafaker.Faker;
import com.oncemore.store.entity.Category;
import com.oncemore.store.entity.Product;
import com.oncemore.store.entity.ProductCategory;
import com.oncemore.store.entity.User;
import com.oncemore.store.repository.CategoryRepository;
import com.oncemore.store.repository.ProductCategoryRepository;
import com.oncemore.store.repository.ProductRepository;
import com.oncemore.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RequestMapping("/init")
@RestController
public class MockController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/categories")
    @Transactional
    public String initCategories() {
        Faker faker = new Faker();
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String categoryName = faker.commerce().department();
            Category category = new Category();
            category.setName(categoryName);
            categories.add(category);
        }
        categoryRepository.saveAll(categories);
        return 5 + " categories initialized successfully.";
    }

    @GetMapping("/product")
    public String initProducts() {
        Faker faker = new Faker();
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            Product product = new Product();
            product.setName(faker.commerce().productName());
            product.setDescription(faker.lorem().sentence());
            product.setPrice(BigDecimal.valueOf(faker.number().randomDouble(2, 10, 1000)));
            product.setQuantity(faker.number().numberBetween(1, 100));
            products.add(product);
        }

        productRepository.saveAll(products);
        return "Initialized " + 15 + " products.";
    }

    @GetMapping("/product-category")
    public String initProCate() {
        Faker faker = new Faker();
        List<ProductCategory> productCategories = new ArrayList<>();

        List<Product> products = productRepository.findAll();
        List<Category> categories = categoryRepository.findAll();

        Set<Integer> chosenProductIndices = new HashSet<>();
        Set<Integer> chosenCategoryIndices = new HashSet<>();

        for (int i = 0; i < 20; i++) {
            int productIndex = faker.number().numberBetween(0, products.size());
            if (chosenProductIndices.contains(productIndex)) {
                break;
            }
            chosenProductIndices.add(productIndex);

            int categoryIndex = faker.number().numberBetween(0, categories.size());
            if (chosenCategoryIndices.contains(categoryIndex)) {
                break;
            }
            chosenCategoryIndices.add(categoryIndex);

            Product product = products.get(productIndex);
            Category category = categories.get(categoryIndex);

            ProductCategory productCategory = new ProductCategory();
            productCategory.setProduct(product);
            productCategory.setCategory(category);
            productCategories.add(productCategory);
        }

            productCategoryRepository.saveAll(productCategories);
        return "OK";
    }

    @GetMapping("/users")
    public String initUser() {
        Faker faker = new Faker();
        List<User> users = new ArrayList<>();
        User admin = new User();
        admin.setName("ADMIN");
        admin.setUsername("admin");
        admin.setPassword("123");
        admin.setRole("ADMIN");
        users.add(admin);

        User user = new User();
        user.setName("User");
        user.setUsername("user");
        user.setPassword("123");
        user.setRole("USER");
        users.add(user);

        userRepository.saveAll(users);

        return "OK";
    }


}
