package com.example.ASM.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CartDetail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Quantity", nullable = false)
    private int quantity;

    // Liên kết nhiều-đến-một với Cart
    @ManyToOne
    @JoinColumn(name = "CartId", nullable = false)
    private Cart cart;

    // Liên kết nhiều-đến-một với Product
    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    private Product product;
}
