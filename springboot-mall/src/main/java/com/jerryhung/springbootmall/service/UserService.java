package com.jerryhung.springbootmall.service;

import com.jerryhung.springbootmall.dto.UserLoginRequest;
import com.jerryhung.springbootmall.dto.UserRegisterRequest;
import com.jerryhung.springbootmall.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer id);

    User login(UserLoginRequest userLoginRequest);
}
