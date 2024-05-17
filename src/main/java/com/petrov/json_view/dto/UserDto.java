package com.petrov.json_view.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.petrov.json_view.entity.OrderEntity;
import com.petrov.json_view.views.Views;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @JsonView(Views.UserSummary.class)
    private Long id;

    @JsonView(Views.UserSummary.class)
    @NotEmpty(message = "Имя не может быть пустым")
    private String name;

    @JsonView(Views.UserSummary.class)
    @Email(message = "Некорректный формат емейл")
    private String email;

    @JsonView(Views.UserDetails.class)
    private List<OrderDto> orders;
}
