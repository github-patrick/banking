package com.granite.banking.controller;

import com.granite.banking.dtos.UserLoginDto;
import com.granite.banking.exceptions.ValidationUtils;
import com.granite.banking.mappers.UserLoginMapper;
import com.granite.banking.model.UserLogin;
import com.granite.banking.service.UserLoginService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(UserLoginApiController.RESOURCE_PATH)
public class UserLoginApiController {

    public static final String RESOURCE_PATH = "/user-login";

    private UserLoginService userLoginService;
    private UserLoginMapper userLoginMapper;

    @Autowired
    public UserLoginApiController(UserLoginService userLoginService, UserLoginMapper userLoginMapper) {
        this.userLoginService = userLoginService;
        this.userLoginMapper = userLoginMapper;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserLoginDto> createUserLogin(@RequestBody @Valid UserLoginDto userLoginDto, BindingResult result) {

        userLoginService.emailExists(userLoginDto.getEmail());

        if (result.hasErrors()) {
            ValidationUtils.handleModelConstraints(
                    result.getFieldError().getField() + " " +
                            result.getFieldError().getDefaultMessage());
        }

        UserLogin userLogin = userLoginMapper.convertToEntity(userLoginDto);
        UserLogin userLoginCreated = userLoginService.createUserLogin(userLogin);

        return new ResponseEntity(userLoginMapper.convertToDto(userLoginCreated), HttpStatus.CREATED);
    }

    @GetMapping(value = "{emailAddress}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserLoginDto> getUserLogin(@PathVariable String emailAddress) {

        UserLogin userLogin = userLoginService.getUserLogin(emailAddress);
        UserLoginDto userLoginDto = userLoginMapper.convertToDto(userLogin);

        return new ResponseEntity<>(userLoginDto, HttpStatus.OK);

    }

}


