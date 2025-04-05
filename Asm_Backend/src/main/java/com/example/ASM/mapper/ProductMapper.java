package com.example.ASM.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.example.ASM.dto.request.Product.ProductRequest;
import com.example.ASM.dto.request.Product.ProductUpdateRequest;
import com.example.ASM.dto.response.product.ProductResponse;
import com.example.ASM.entity.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "cartDetails", source = "cartDetails", qualifiedByName = "mapCartDetails")
    @Mapping(target = "favoriteProducts", source = "favoriteProducts", qualifiedByName = "mapFavoriteProducts")
    @Mapping(target = "images", source = "images", qualifiedByName = "mapImages")
    @Mapping(target = "orderDetails", source = "orderDetails", qualifiedByName = "mapOrderDetails")
    Product toProduct(ProductRequest request);

    @Mapping(target = "category", source = "category.categoryName")
    @Mapping(target = "productType", source = "productType.nameType")
    @Mapping(target = "cartDetails", source = "cartDetails", qualifiedByName = "mapCartDetailsToIds")
    @Mapping(target = "favoriteProducts", source = "favoriteProducts", qualifiedByName = "mapFavoriteProductsToIds")
    @Mapping(target = "orderDetails", source = "orderDetails", qualifiedByName = "mapOrderDetailsToIds")
    ProductResponse toProductResponse(Product entity);

    void updateProduct(@MappingTarget Product entity, ProductUpdateRequest request);

    default Category map(int categoryId) {
        Category category = new Category();
        category.setId(categoryId);
        return category;
    }

    default int map(Category category) {
        return category != null ? category.getId() : 0;
    }

    default ProductType mapProductType(int productTypeId) {
        ProductType productType = new ProductType();
        productType.setId(productTypeId);
        return productType;
    }

    default int mapProductType(ProductType productType) {
        return productType != null ? productType.getId() : 0;
    }

    default List<String> map(List<Image> images) {
        if (images == null) return Collections.emptyList();
        return images.stream().map(image -> image.getUrl()).collect(Collectors.toList());
    }

    @Named("mapCartDetailsToIds")
    default List<String> mapCartDetailsToIds(List<CartDetail> cartDetails) {
        if (cartDetails == null) return Collections.emptyList();
        return cartDetails.stream()
                .map(cartDetail -> String.valueOf(cartDetail.getId()))
                .collect(Collectors.toList());
    }

    @Named("mapFavoriteProductsToIds")
    default List<String> mapFavoriteProductsToIds(List<FavoriteProduct> favoriteProducts) {
        if (favoriteProducts == null) return Collections.emptyList();
        return favoriteProducts.stream()
                .map(favoriteProduct -> String.valueOf(favoriteProduct.getId()))
                .collect(Collectors.toList());
    }

    @Named("mapOrderDetailsToIds")
    default List<String> mapOrderDetailsToIds(List<OrderDetail> orderDetails) {
        if (orderDetails == null) return Collections.emptyList();
        return orderDetails.stream()
                .map(orderDetail -> String.valueOf(orderDetail.getId()))
                .collect(Collectors.toList());
    }

    @Named("mapCartDetails")
    default List<CartDetail> mapCartDetails(List<String> cartDetailIds) {
        if (cartDetailIds == null) return Collections.emptyList();
        return cartDetailIds.stream()
                .map(id -> {
                    CartDetail cartDetail = new CartDetail();
                    cartDetail.setId(Integer.parseInt(id));
                    return cartDetail;
                })
                .collect(Collectors.toList());
    }

    @Named("mapFavoriteProducts")
    default List<FavoriteProduct> mapFavoriteProducts(List<String> favoriteProductIds) {
        if (favoriteProductIds == null) return Collections.emptyList();
        return favoriteProductIds.stream()
                .map(id -> {
                    FavoriteProduct favoriteProduct = new FavoriteProduct();
                    favoriteProduct.setId(Integer.parseInt(id));
                    return favoriteProduct;
                })
                .collect(Collectors.toList());
    }

    @Named("mapImages")
    default List<Image> mapImages(List<String> imageIds) {
        if (imageIds == null) return Collections.emptyList();
        return imageIds.stream()
                .map(id -> {
                    Image image = new Image();
                    image.setId(Integer.parseInt(id));
                    return image;
                })
                .collect(Collectors.toList());
    }

    @Named("mapOrderDetails")
    default List<OrderDetail> mapOrderDetails(List<String> orderDetailIds) {
        if (orderDetailIds == null) return Collections.emptyList();
        return orderDetailIds.stream()
                .map(id -> {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setId(Integer.parseInt(id));
                    return orderDetail;
                })
                .collect(Collectors.toList());
    }
}
