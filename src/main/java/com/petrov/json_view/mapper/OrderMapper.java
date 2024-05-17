package com.petrov.json_view.mapper;

import com.petrov.json_view.dto.OrderDto;
import com.petrov.json_view.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    OrderEntity toOrderEntity(OrderDto orderDto);
    OrderDto toOrderDto(OrderEntity orderEntity);
    List<OrderEntity> toOrderEntityList(List<OrderDto> orderDtoList);
    List<OrderDto> toOrderDtoList(List<OrderEntity> orderEntityList);
}
