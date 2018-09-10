package com.granite.banking.dtos;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserLoginDto {

    private Long userLoginId;

    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String email;

    @NotEmpty
    @NotNull
    private String password;
}
