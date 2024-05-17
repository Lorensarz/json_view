package com.petrov.json_view.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.petrov.json_view.views.Views;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    @JsonView(Views.UserDetails.class)
    private Long productId;

    @JsonView(Views.UserDetails.class)
    private String name;

    @JsonView(Views.UserDetails.class)
    private BigDecimal price;
}


