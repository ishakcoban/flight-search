package com.example.flightSearch.mapper;

import com.example.flightSearch.entity.User;
import com.example.flightSearch.modal.dto.UserDto;
import com.example.flightSearch.modal.request.RegisterRequest;
import com.example.flightSearch.modal.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    public UserDto toDto(User user){

        return UserDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public User register(RegisterRequest request) {

        return User.builder()
                .userName(request.getUserName())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
    }

    public User update(User user, UserUpdateRequest userUpdateRequest) {

        return User.builder()
                .id(user.getId())
                .userName(userUpdateRequest.getUserName())
                .firstName(userUpdateRequest.getFirstName())
                .lastName(userUpdateRequest.getLastName())
                .email(userUpdateRequest.getEmail())
                .password(passwordEncoder.encode(userUpdateRequest.getPassword()))
                .build();
    }

}
