package com.poly.asm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

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
