package com.petrov.json_view.mapper;

import com.petrov.json_view.dto.UserDto;
import com.petrov.json_view.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserEntity toUserEntity(UserDto userDto);
    UserDto toUserDto(UserEntity userEntity);
    List<UserEntity> toUserEntityList(List<UserDto> userDtoList);
    List<UserDto> toUserDtoList(List<UserEntity> userEntityList);


}
