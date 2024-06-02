package com.oncemore.store.repository;

import com.oncemore.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByStatus(boolean status);

    Product findByIdAndStatus(UUID id, boolean status);
}
