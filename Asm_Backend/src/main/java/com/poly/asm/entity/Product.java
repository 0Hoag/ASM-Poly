package com.poly.asm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "CreatedAt", nullable = false)
    private Timestamp createdAt;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price", nullable = false)
    private double price;

    @Column(name = "ProductName", nullable = false)
    private String productName;

    @Column(name = "SalePrice")
    private double salePrice;

    @Column(name = "SoldQuantity", nullable = false)
    private int soldQuantity;

    @Column(name = "StockQuantity", nullable = false)
    private int stockQuantity;

    // Liên kết một-đến-nhiều với CartDetail
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CartDetail> cartDetails;

    // Liên kết một-đến-nhiều với FavoriteProduct
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)

    private List<FavoriteProduct> favoriteProducts;

    // Liên kết một-đến-nhiều với Image
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> images;

    // Liên kết một-đến-nhiều với OrderDetail
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    // Liên kết nhiều-đến-một với Category
    @ManyToOne
    @JoinColumn(name = "CategoryId", nullable = false)
    private Category category;

    // Liên kết nhiều-đến-một với ProductType
    @ManyToOne
    @JoinColumn(name = "ProductTypeId", nullable = false)
    private ProductType productType;

    @Override
    public String toString() {
        return "Product{" + "id="
                + id + ", createdAt='"
                + createdAt + '\'' + ", description='"
                + description + '\'' + ", price="
                + price + ", productName='"
                + productName + '\'' + ", salePrice="
                + salePrice + ", soldQuantity="
                + soldQuantity + ", stockQuantity="
                + stockQuantity + ", cartDetailsCount="
                + (cartDetails != null ? cartDetails.size() : 0) + ", favoriteProductsCount="
                + (favoriteProducts != null ? favoriteProducts.size() : 0) + ", imagesCount="
                + (images != null ? images.size() : 0) + ", orderDetailsCount="
                + (orderDetails != null ? orderDetails.size() : 0) + ", category="
                + (category != null ? category.getId() : null) + ", productType="
                + (productType != null ? productType.getId() : null) + '}';
    }
}
