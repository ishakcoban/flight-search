package com.example.flightSearch.modal.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    private String userName;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String password;

}
