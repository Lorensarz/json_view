package com.petrov.json_view.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class OrderCreationRequestDto {

    private Long userId;

    private OrderDto orderDto;

}
