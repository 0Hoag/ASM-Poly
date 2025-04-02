package com.example.ASM.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Image")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Url", nullable = false)
    private String url;

    // Liên kết nhiều-đến-một với Product
    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    private Product product;
}
