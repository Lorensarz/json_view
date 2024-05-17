package com.petrov.json_view.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.petrov.json_view.entity.UserEntity;
import com.petrov.json_view.entity.enums.Status;
import com.petrov.json_view.views.Views;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    @JsonView(Views.UserDetails.class)
    private Long id;

    @JsonView(Views.UserDetails.class)
    private List<ProductDto> products;

    @JsonView(Views.UserDetails.class)
    private BigDecimal orderAmount;

    @JsonView(Views.UserDetails.class)
    private UserEntity user;

    @JsonView(Views.UserDetails.class)
    private Status status;
}
