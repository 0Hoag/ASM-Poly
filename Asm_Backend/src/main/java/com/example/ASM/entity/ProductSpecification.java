package com.example.ASM.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ProductSpecification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSpecification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Value", nullable = false)
    private String value;

    // Liên kết nhiều-đến-một với SpecificationType
    @ManyToOne
    @JoinColumn(name = "SpecTypeId", nullable = false)
    private SpecificationType specificationType;

    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    private Product product;
}
