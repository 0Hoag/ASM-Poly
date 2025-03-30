package com.example.ASM.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "Id")
    private int id;

    // Liên kết nhiều-đến-một với User
    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    // Liên kết một-đến-nhiều với CartDetail
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartDetail> cartDetails;

    @Column(name = "CreatedAt", nullable = false, updatable = false)
    private Timestamp createdAt;
}
