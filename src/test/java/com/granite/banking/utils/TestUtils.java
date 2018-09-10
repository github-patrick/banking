package com.granite.banking.utils;

import com.granite.banking.dtos.UserLoginDto;
import com.granite.banking.model.UserLogin;


public class TestUtils {



    public static UserLoginDto getDefaultValidUserLoginDto() {
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail("matthew.jones@sentiapps.com");
        userLoginDto.setPassword("password");
        return userLoginDto;
    }

    public static UserLogin getDefaultValidUserLogin() {
        UserLogin userLogin = new UserLogin();
        userLogin.setEmail("matthew.jones@sentiapps.com");
        userLogin.setPassword("password");
        return userLogin;
    }

}
