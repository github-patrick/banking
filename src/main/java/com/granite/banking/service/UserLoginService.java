package com.granite.banking.service;

import com.granite.banking.exceptions.ErrorMessages;
import com.granite.banking.exceptions.ValidationUtils;
import com.granite.banking.model.UserLogin;
import com.granite.banking.repositories.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    private UserLoginRepository userLoginRepository;

    @Autowired
    public UserLoginService(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    public UserLogin createUserLogin(UserLogin userLogin) {
        return userLoginRepository.save(userLogin);
    }

    public UserLogin getUserLogin(String emailAddress) {

        if (!userLoginRepository.findByEmail(emailAddress).isPresent()) {
            ValidationUtils.handleUserDetailsNotFoundException(ErrorMessages.USER_DETAILS_NOT_FOUND);
        }
        return userLoginRepository.findByEmail(emailAddress).get();
    }

    public void emailExists(String email) {
        if (userLoginRepository.findByEmail(email).isPresent()) {
            ValidationUtils.handleEmailExistsException(ErrorMessages.USER_DETAILS_EMAIL_EXISTS);
        }
    }
}
