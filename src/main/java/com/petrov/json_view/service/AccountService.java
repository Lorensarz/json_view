package com.petrov.json_view.service;

import com.petrov.json_view.dto.OrderCreationRequestDto;
import com.petrov.json_view.dto.OrderDto;
import com.petrov.json_view.dto.UserDto;

import java.util.List;

public interface AccountService {

    void save(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto findById(Long id);

    void deleteUser(Long id);

    void updateUser(Long id, UserDto userDto);

    void createOrder(OrderCreationRequestDto orderCreationRequestDto);
}
