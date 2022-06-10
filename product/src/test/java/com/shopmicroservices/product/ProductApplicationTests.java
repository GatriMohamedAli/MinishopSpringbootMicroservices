package com.shopmicroservices.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class ProductApplicationTests {
    @Container
    static PostgreSQLContainer postgreSQLContainer=new PostgreSQLContainer("postgres");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @DynamicPropertySource
    static void setProprieties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.database.url",postgreSQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.database.username",postgreSQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.database.password",postgreSQLContainer::getPassword);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        ProductRequest productRequest=getProductRequest();

        mockMvc.perform(MockMvcRequestBuilders.post("api/v1/product")
                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                .content(objectMapper.writeValueAsString(productRequest))

        ).andExpect(status().isCreated());
    }

    private ProductRequest getProductRequest(){
        return ProductRequest.builder()
                .name("Iphone 8")
                .description("Iphone 8")
                .price(BigDecimal.valueOf(800))
                .build();
    }
}