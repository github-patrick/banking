package com.granite.banking.mappers;

import com.granite.banking.dtos.UserLoginDto;
import com.granite.banking.model.UserLogin;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserLoginMapper {

    private ModelMapper modelMapper;

    public UserLoginMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserLoginDto convertToDto(UserLogin userLogin) {
        com.granite.banking.dtos.UserLoginDto userLoginDto = modelMapper.map(userLogin, com.granite.banking.dtos.UserLoginDto.class);
        return userLoginDto;
    }

    public UserLogin convertToEntity(UserLoginDto userLoginDto) {
        UserLogin userLogin = modelMapper.map(userLoginDto, UserLogin.class);
        return userLogin;
    }
}

