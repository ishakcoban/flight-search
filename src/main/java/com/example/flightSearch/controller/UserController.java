package com.example.flightSearch.controller;

import com.example.flightSearch.modal.dto.UserDto;
import com.example.flightSearch.modal.request.UserUpdateRequest;
import com.example.flightSearch.service.UserService;
import com.example.flightSearch.utils.MdcConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@SecurityRequirement(name="bearerAuth")
public class UserController {

    private final UserService userService;

    @Operation(description = "Get an user information.")
    @GetMapping
    public UserDto get() {
        return userService.get(Long.valueOf(MDC.get(MdcConstant.X_USER_ID)));
    }

    @Operation(description = "Update an user information.")
    @PutMapping
    public void update(@RequestBody UserUpdateRequest userUpdateRequest) {
        userService.update(Long.valueOf(MDC.get(MdcConstant.X_USER_ID)),userUpdateRequest);
    }

    @Operation(description = "Delete an user.")
    @DeleteMapping
    public void delete() {
       userService.delete(Long.valueOf(MDC.get(MdcConstant.X_USER_ID)));
    }

}

