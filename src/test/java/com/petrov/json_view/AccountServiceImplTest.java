package com.petrov.json_view;

import com.petrov.json_view.dto.OrderCreationRequestDto;
import com.petrov.json_view.dto.OrderDto;
import com.petrov.json_view.dto.ProductDto;
import com.petrov.json_view.dto.UserDto;
import com.petrov.json_view.entity.OrderEntity;
import com.petrov.json_view.entity.UserEntity;
import com.petrov.json_view.mapper.ProductMapper;
import com.petrov.json_view.mapper.UserMapper;
import com.petrov.json_view.repository.OrderRepository;
import com.petrov.json_view.repository.ProductRepository;
import com.petrov.json_view.repository.UserRepository;
import com.petrov.json_view.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    private UserEntity userEntity;
    private UserDto userDto;
    private OrderCreationRequestDto orderCreationRequestDto;
    private OrderDto orderDto;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("Test User");

        userDto = new UserDto();
        userDto.setName("Test User");
        userDto.setEmail("test@example.com");

        orderCreationRequestDto = new OrderCreationRequestDto();
        orderCreationRequestDto.setUserId(1L);
        orderCreationRequestDto.setOrderDto(new OrderDto());

        orderDto = new OrderDto();
        orderDto.setProducts(Arrays.asList(new ProductDto(), new ProductDto()));

    }

    @Test
    void testSaveUser() {
        when(userMapper.toUserEntity(any())).thenReturn(userEntity);
        when(userRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        accountService.save(userDto);

        verify(userMapper, times(1)).toUserEntity(userDto);
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    void testGetAllUsers() {
        List<UserEntity> mockUsers = Arrays.asList(userEntity);
        when(userRepository.findAll()).thenReturn(mockUsers);
        when(userMapper.toUserDtoList(any())).thenReturn(Arrays.asList(userDto));

        List<UserDto> result = accountService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals(userDto.getName(), result.get(0).getName());
    }

    @Test
    void testFindById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(userMapper.toUserDto(any())).thenReturn(userDto);

        UserDto result = accountService.findById(1L);

        assertNotNull(result);
        assertEquals(userDto.getName(), result.getName());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        accountService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(userEntity));

        accountService.updateUser(1L, userDto);

        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    void testCreateOrder() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(orderRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        accountService.createOrder(orderCreationRequestDto);

        verify(userRepository, times(1)).findById(1L);
        verify(productMapper, times(1)).toProductEntityList(orderCreationRequestDto.getOrderDto().getProducts());
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }
}

