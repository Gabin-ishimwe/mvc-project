package com.mvc.Productservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(
        value = "products"
)
@Data
@Builder
public class ProductModel {
    @Id
    private String id;
    private String name;
    private String description;
    private String price;
}
