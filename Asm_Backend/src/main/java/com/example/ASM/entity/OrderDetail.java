package com.example.ASM.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OrderDetail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "CurrentPrice", nullable = false)
    private double currentPrice;

    @Column(name = "Quantity", nullable = false)
    private int quantity;

    // Liên kết nhiều-đến-một với Order
    @ManyToOne
    @JoinColumn(name = "OrderId", nullable = false)
    private Order order;

    // Liên kết nhiều-đến-một với Product
    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    private Product product;
}
