package com.example.ASM.mapper;

import com.example.ASM.dto.request.Product.ProductRequest;
import com.example.ASM.dto.request.Product.ProductUpdateRequest;
import com.example.ASM.dto.response.product.ImageResponse;
import com.example.ASM.dto.response.product.ProductResponse;
import com.example.ASM.entity.Category;
import com.example.ASM.entity.Image;
import com.example.ASM.entity.Product;
import com.example.ASM.entity.ProductType;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductRequest request) {
        if ( request == null ) {
            return null;
        }

        Product product = new Product();

        product.setCartDetails( mapCartDetails( request.getCartDetails() ) );
        product.setFavoriteProducts( mapFavoriteProducts( request.getFavoriteProducts() ) );
        product.setImages( mapImages( request.getImages() ) );
        product.setOrderDetails( mapOrderDetails( request.getOrderDetails() ) );
        product.setDescription( request.getDescription() );
        product.setPrice( request.getPrice() );
        product.setProductName( request.getProductName() );
        product.setSalePrice( request.getSalePrice() );
        product.setSoldQuantity( request.getSoldQuantity() );
        product.setStockQuantity( request.getStockQuantity() );
        product.setCategory( map( request.getCategory() ) );
        product.setProductType( mapProductType( request.getProductType() ) );

        return product;
    }

    @Override
    public ProductResponse toProductResponse(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductResponse.ProductResponseBuilder productResponse = ProductResponse.builder();

        productResponse.category( entityCategoryCategoryName( entity ) );
        productResponse.productType( entityProductTypeNameType( entity ) );
        productResponse.cartDetails( mapCartDetailsToIds( entity.getCartDetails() ) );
        productResponse.favoriteProducts( mapFavoriteProductsToIds( entity.getFavoriteProducts() ) );
        productResponse.orderDetails( mapOrderDetailsToIds( entity.getOrderDetails() ) );
        productResponse.id( entity.getId() );
        productResponse.productName( entity.getProductName() );
        productResponse.description( entity.getDescription() );
        productResponse.price( entity.getPrice() );
        productResponse.salePrice( entity.getSalePrice() );
        productResponse.soldQuantity( entity.getSoldQuantity() );
        productResponse.stockQuantity( entity.getStockQuantity() );
        productResponse.createdAt( entity.getCreatedAt() );
        productResponse.images( imageListToImageResponseList( entity.getImages() ) );

        return productResponse.build();
    }

    @Override
    public void updateProduct(Product entity, ProductUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        entity.setDescription( request.getDescription() );
        entity.setPrice( request.getPrice() );
        entity.setProductName( request.getProductName() );
        entity.setSalePrice( request.getSalePrice() );
        entity.setSoldQuantity( request.getSoldQuantity() );
        entity.setStockQuantity( request.getStockQuantity() );
        entity.setCategory( map( request.getCategory() ) );
        entity.setProductType( mapProductType( request.getProductType() ) );
    }

    private String entityCategoryCategoryName(Product product) {
        if ( product == null ) {
            return null;
        }
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        String categoryName = category.getCategoryName();
        if ( categoryName == null ) {
            return null;
        }
        return categoryName;
    }

    private String entityProductTypeNameType(Product product) {
        if ( product == null ) {
            return null;
        }
        ProductType productType = product.getProductType();
        if ( productType == null ) {
            return null;
        }
        String nameType = productType.getNameType();
        if ( nameType == null ) {
            return null;
        }
        return nameType;
    }

    protected ImageResponse imageToImageResponse(Image image) {
        if ( image == null ) {
            return null;
        }

        ImageResponse.ImageResponseBuilder imageResponse = ImageResponse.builder();

        imageResponse.id( image.getId() );
        imageResponse.url( image.getUrl() );

        return imageResponse.build();
    }

    protected List<ImageResponse> imageListToImageResponseList(List<Image> list) {
        if ( list == null ) {
            return null;
        }

        List<ImageResponse> list1 = new ArrayList<ImageResponse>( list.size() );
        for ( Image image : list ) {
            list1.add( imageToImageResponse( image ) );
        }

        return list1;
    }
}
