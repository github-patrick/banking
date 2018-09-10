package com.granite.banking.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userLoginId;

    @Column(unique = true)
    private String email;

    @NotNull
    private String password;
}
