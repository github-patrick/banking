package com.granite.banking.service;

import com.granite.banking.exceptions.EmailExistsException;
import com.granite.banking.exceptions.UserDetailsNotFoundException;
import com.granite.banking.model.UserLogin;
import com.granite.banking.repositories.UserLoginRepository;
import com.granite.banking.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserLoginServiceTest {

    private UserLoginService userLoginService;

    @Mock
    private UserLoginRepository userLoginRepository;

    @Before
    public void setUp() {
        userLoginService = new UserLoginService(userLoginRepository);
    }

    @Test(expected = UserDetailsNotFoundException.class)
    public void getUserLogin() {

        UserLogin userLogin = TestUtils.getDefaultValidUserLogin();

        when(userLoginRepository.findByEmail(userLogin.getEmail())).thenReturn(Optional.empty());
        userLoginService.getUserLogin(userLogin.getEmail());
    }

    @Test(expected = EmailExistsException.class)
    public void emailExists() {
        UserLogin userLogin = TestUtils.getDefaultValidUserLogin();

        when(userLoginRepository.findByEmail(userLogin.getEmail())).thenReturn(Optional.of(userLogin));
        userLoginService.emailExists(userLogin.getEmail());
    }

}