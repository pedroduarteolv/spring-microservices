package com.pedro.microservices.product.service;

import com.pedro.microservices.product.dto.ProductRequest;
import com.pedro.microservices.product.dto.ProductResponse;
import com.pedro.microservices.product.model.Product;
import com.pedro.microservices.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldCreateProductSuccessfully() {
        //Arrange
        ProductRequest productRequest = new ProductRequest("Iphone 15", "Iphone 15 pro max", BigDecimal.valueOf(1000));

        Product product = Product.builder()
                .id("1")
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(product);

        //Act

        ProductResponse response = productService.createProduct(productRequest);

        //Assert

        assertNotNull(response);
        assertEquals("1", product.getId());
        assertEquals("Iphone 15", response.name());
        assertEquals("Iphone 15 pro max", response.description());
        assertEquals(BigDecimal.valueOf(1000), response.price());

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void shouldGetProductsSuccessfully(){
        //Arrange
        Product product = Product.builder()
                .id("1")
                .name("Iphone 15")
                .description("Iphone 15 pro max")
                .price(BigDecimal.valueOf(1000))
                .build();

        when(productRepository.findAll()).thenReturn(List.of(product));

        //Act
        List<ProductResponse> response = productService.getAllProducts();

        //Assert
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Iphone 15", response.get(0).name());
        assertEquals("Iphone 15 pro max", response.get(0).description());
        assertEquals(BigDecimal.valueOf(1000), response.get(0).price());

        verify(productRepository, times(1)).findAll();
    }


}