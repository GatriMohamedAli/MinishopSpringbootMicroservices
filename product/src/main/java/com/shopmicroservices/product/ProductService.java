package com.shopmicroservices.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ProductService(ProductRepository productRepository) {

    public void createProduct(ProductRequest productRequest) {
        Product product=Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
    }

    public List<ProductResponse> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    public ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
