package com.jerryhung.springbootmall.service.impl;

import com.jerryhung.springbootmall.dao.UserDao;
import com.jerryhung.springbootmall.dto.UserRegisterRequest;
import com.jerryhung.springbootmall.model.User;
import com.jerryhung.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }
}
