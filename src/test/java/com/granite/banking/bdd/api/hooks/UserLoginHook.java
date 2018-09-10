package com.granite.banking.bdd.api.hooks;

import com.granite.banking.repositories.UserLoginRepository;
import cucumber.api.java.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class UserLoginHook {


    @Autowired
    private UserLoginRepository userLoginRepository;

    @Before("@UserLogin")
    public void setUp() throws Exception {
        userLoginRepository.deleteAll();
    }
}