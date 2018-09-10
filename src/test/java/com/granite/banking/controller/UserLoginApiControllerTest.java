package com.granite.banking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.granite.banking.dtos.UserLoginDto;
import com.granite.banking.exceptions.CustomizedResponseEntityExceptionHandler;
import com.granite.banking.mappers.UserLoginMapper;
import com.granite.banking.model.UserLogin;
import com.granite.banking.service.UserLoginService;
import com.granite.banking.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class UserLoginApiControllerTest {

    private UserLoginApiController userLoginApiController;

    @Mock
    private UserLoginService userLoginService;

    @Mock
    private UserLoginMapper userLoginMapper;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        userLoginApiController = new UserLoginApiController(userLoginService, userLoginMapper);
        mockMvc = MockMvcBuilders.standaloneSetup(userLoginApiController).
                setControllerAdvice(new CustomizedResponseEntityExceptionHandler()).build();
    }

    @Test
    public void createUserLogin() throws Exception {

        UserLoginDto userLoginDto = TestUtils.getDefaultValidUserLoginDto();
        UserLogin userLogin = TestUtils.getDefaultValidUserLogin();

        when(userLoginService.createUserLogin(any(UserLogin.class))).thenReturn(TestUtils.getDefaultValidUserLogin());
        when(userLoginMapper.convertToEntity(userLoginDto)).thenReturn(userLogin);
        userLoginDto.setUserLoginId(new Long(1));
        when(userLoginMapper.convertToDto(userLogin)).thenReturn(userLoginDto);

        mockMvc.perform(post(UserLoginApiController.RESOURCE_PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(userLoginDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userLoginId", is(1)))
                .andExpect(jsonPath("$.email", is(userLoginDto.getEmail())))
                .andExpect(jsonPath("$.password", is(userLoginDto.getPassword())));
    }

    @Test
    public void createUserLoginBadRequestEmptyEmailAndPassword() throws Exception {
        mockMvc.perform(post(UserLoginApiController.RESOURCE_PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsString(new UserLoginDto())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createUserLoginBadRequestDuplicateEmail() {

    }

    @Test
    public void getUserLogin() throws Exception {
        mockMvc.perform(get(UserLoginApiController.RESOURCE_PATH + "/1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getUserLoginNotFound() throws Exception {


        mockMvc.perform(get(UserLoginApiController.RESOURCE_PATH + "peter.hitchens@sentiapps.com")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }
}