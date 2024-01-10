package com.example.flightSearch.controller;

import com.example.flightSearch.modal.dto.UserDto;
import com.example.flightSearch.modal.request.UserUpdateRequest;
import com.example.flightSearch.service.UserService;
import com.example.flightSearch.utils.MdcConstant;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserDto get() {
        return userService.getById(Long.valueOf(MDC.get(MdcConstant.X_USER_ID)));
    }

    @PutMapping
    public void update(@RequestBody UserUpdateRequest userUpdateRequest) {
        userService.update(Long.valueOf(MDC.get(MdcConstant.X_USER_ID)),userUpdateRequest);
    }

    @DeleteMapping
    public void delete() {
       userService.delete(Long.valueOf(MDC.get(MdcConstant.X_USER_ID)));
    }

}

