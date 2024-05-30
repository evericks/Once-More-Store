package com.oncemore.store.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ProductCategory")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "CategoryId", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    private Product product;
}
