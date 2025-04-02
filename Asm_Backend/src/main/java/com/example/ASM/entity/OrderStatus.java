package com.example.ASM.entity;

import java.util.List;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OrderStatus")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "StatusName", nullable = false)
    private String statusName;

    // Liên kết một-đến-nhiều với Order
    @OneToMany(mappedBy = "orderStatus")
    private List<Order> orders;
}
