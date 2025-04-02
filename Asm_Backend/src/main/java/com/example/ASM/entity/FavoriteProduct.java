package com.example.ASM.entity;

import java.sql.Timestamp;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FavoriteProduct")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "LikedAt", nullable = false)
    private Timestamp likedAt;

    // Liên kết nhiều-đến-một với Product
    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    private Product product;

    // Liên kết nhiều-đến-một với User
    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private User user;
}
