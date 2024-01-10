package com.example.flightSearch.modal.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;

}
