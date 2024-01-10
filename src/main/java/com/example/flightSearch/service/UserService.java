package com.example.flightSearch.service;

import com.example.flightSearch.entity.User;
import com.example.flightSearch.mapper.UserMapper;
import com.example.flightSearch.modal.dto.UserDto;
import com.example.flightSearch.modal.request.UserUpdateRequest;
import com.example.flightSearch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void checkEmailExist(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The email already taken!");
        }
    }

    public UserDto getById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow());
    }

    public void update(Long id, UserUpdateRequest userUpdateRequest) {

        User user = userMapper.update(userRepository.findById(id).orElseThrow(),userUpdateRequest);
        userRepository.save(user);
    }

    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }
}
