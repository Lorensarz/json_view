package com.petrov.json_view.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.petrov.json_view.dto.OrderCreationRequestDto;
import com.petrov.json_view.dto.OrderDto;
import com.petrov.json_view.views.Views;
import com.petrov.json_view.dto.UserDto;
import com.petrov.json_view.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void crateUser(@RequestBody @Validated UserDto userDto) {
        accountService.save(userDto);
    }

    @GetMapping
    @JsonView(Views.UserSummary.class)
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserDto> getAllUsers() {
       return accountService.getAllUsers();
    }

    @GetMapping("/{id}")
    @JsonView(Views.UserDetails.class)
    public UserDto getUserById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteUser(@PathVariable Long id) {
        accountService.deleteUser(id);
    }

    @PatchMapping("/{id}")
    public void updateUser(@PathVariable Long id,
            @RequestBody @Validated UserDto userDto) {
        accountService.updateUser(id, userDto);
    }

    @PostMapping("/create_order")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderCreationRequestDto orderCreationRequestDto) {
        accountService.createOrder(orderCreationRequestDto);
    }
}
