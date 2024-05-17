package com.petrov.json_view.mapper;

import com.petrov.json_view.dto.ProductDto;
import com.petrov.json_view.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductEntity toProductEntity(ProductDto productDto);

    ProductDto toProductDto(ProductEntity productEntity);

    List<ProductDto> toProductDtoList(List<ProductEntity> productEntityList);

    List<ProductEntity> toProductEntityList(List<ProductDto> productDtoList);

}
