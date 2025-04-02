package com.example.ASM.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "CreatedAt", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "TotalAmount", nullable = false)
    private double totalAmount;

    // Liên kết một-đến-nhiều với OrderDetail
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;

    // Liên kết nhiều-đến-một với Address
    @ManyToOne
    @JoinColumn(name = "AddressId", nullable = false)
    private Address address;

    // Liên kết nhiều-đến-một với OrderStatus
    @ManyToOne
    @JoinColumn(name = "OrderStatusId", nullable = false)
    private OrderStatus orderStatus;

    // Liên kết nhiều-đến-một với User
    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private User user;
}
