package com.mvc.Productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
    private String name;
    private String description;
    private String price;
}
