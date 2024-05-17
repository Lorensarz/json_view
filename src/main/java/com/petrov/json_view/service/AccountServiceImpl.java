package com.petrov.json_view.service;

import com.petrov.json_view.dto.OrderCreationRequestDto;
import com.petrov.json_view.dto.OrderDto;
import com.petrov.json_view.dto.UserDto;
import com.petrov.json_view.entity.OrderEntity;
import com.petrov.json_view.entity.ProductEntity;
import com.petrov.json_view.entity.UserEntity;
import com.petrov.json_view.mapper.OrderMapper;
import com.petrov.json_view.mapper.ProductMapper;
import com.petrov.json_view.mapper.UserMapper;
import com.petrov.json_view.repository.OrderRepository;
import com.petrov.json_view.repository.ProductRepository;
import com.petrov.json_view.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserMapper userMapper;
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public void save(UserDto userDto) {
        UserEntity userEntity = userMapper.toUserEntity(userDto);
        userRepository.save(userEntity);

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userMapper.toUserDtoList(userEntityList);
    }

    @Override
    public UserDto findById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity == null) {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }
        return userMapper.toUserDto(userEntity);
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(Long id, UserDto userDto) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            UserEntity existingUser = optionalUser.get();

            if (userDto.getName() != null) {
                existingUser.setName(userDto.getName());
            }

            if (userDto.getEmail() != null) {
                existingUser.setEmail(userDto.getEmail());
            }

            if (userDto.getOrders() != null) {
                existingUser.setOrders(orderMapper.toOrderEntityList(userDto.getOrders()));
            }

            userRepository.save(existingUser);

        } else {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }
    }

    @Override
    public void createOrder(OrderCreationRequestDto orderCreationRequestDto) {
        Long userId = orderCreationRequestDto.getUserId();
        Optional<UserEntity> user = userRepository.findById(userId);

        if (user.isPresent()) {
            UserEntity existingUser = user.get();
            List<OrderEntity> orders = checkOrders(existingUser);
            List<ProductEntity> products = checkProducts(orderCreationRequestDto.getOrderDto());

            productRepository.saveAll(products);

            OrderEntity order = new OrderEntity();

            order.setUser(existingUser);
            order.setProducts(products);
            order.setOrderAmount(calculateOrderAmount(products));
            orderRepository.save(order);

            orders.add(order);
            existingUser.setOrders(orders);

            userRepository.save(existingUser);

        } else {
            throw new IllegalArgumentException("User with id " + userId + " not found");
        }
    }

    private List<OrderEntity> checkOrders(UserEntity userEntity) {
        List<OrderEntity> orders = userEntity.getOrders();
        if (orders != null) {
            return orders;
        } else {
            return new ArrayList<>() {
            };
        }
    }

    private List<ProductEntity> checkProducts(OrderDto orderDto) {
        List<ProductEntity> products = productMapper.toProductEntityList(orderDto.getProducts());
        if (products != null) {
            return products;
        } else {
            throw new IllegalArgumentException("Add products in order!");
        }
    }

    private BigDecimal calculateOrderAmount(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(ProductEntity::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
