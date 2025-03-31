package com.example.ASM.entity;

import java.util.List;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SpecificationType")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "SpecName", nullable = false)
    private String specName;

    // Liên kết với ProductSpecification (1 SpecificationType có nhiều ProductSpecification)
    @OneToMany(mappedBy = "specificationType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSpecification> productSpecifications;

    // Liên kết với ProductType (nhiều SpecificationType có thể thuộc 1 ProductType)
    @ManyToOne
    @JoinColumn(name = "ProductTypeId", nullable = false)
    private ProductType productType;
}
