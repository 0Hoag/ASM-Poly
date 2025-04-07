package com.example.ASM.entity;

import java.util.List;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Address")
    private String address;

    @Column(name = "IsDefault")
    private boolean defaultAddress;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "Username")
    private String username;

    // Liên kết nhiều-đến-một với User
    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;

    // Liên kết một-đến-nhiều với Order
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;
}
