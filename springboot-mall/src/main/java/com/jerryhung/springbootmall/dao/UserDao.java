package com.jerryhung.springbootmall.dao;

import com.jerryhung.springbootmall.dto.UserRegisterRequest;
import com.jerryhung.springbootmall.model.User;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer id);
}
