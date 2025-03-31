package com.example.ASM.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "CreatedAt", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "FullName", nullable = false)
    private String fullName;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "PhoneNumber", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "Role", nullable = false)
    private boolean role;

    // Liên kết với Address
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

    // Liên kết với Cart
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts;

    // Liên kết với FavoriteProduct
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoriteProduct> favoriteProducts;

    // Liên kết với Order
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;
}
