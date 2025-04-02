package com.example.ASM.entity;

import java.util.List;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "CategoryName", nullable = false)
    private String categoryName;

    // Liên kết nhiều-đến-một với chính nó (danh mục cha)
    @ManyToOne
    @JoinColumn(name = "ParentCategory")
    private Category parentCategory;

    // Liên kết một-đến-nhiều với danh mục con
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> subCategories;

    // Liên kết một-đến-nhiều với sản phẩm
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;
}
