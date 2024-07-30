package com.jerryhung.springbootmall.service.impl;

import com.jerryhung.springbootmall.dao.UserDao;
import com.jerryhung.springbootmall.dto.UserRegisterRequest;
import com.jerryhung.springbootmall.model.User;
import com.jerryhung.springbootmall.service.UserService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {

        // Check whether this email has been taken or not
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if (user != null) {
            log.warn("This email {} has been registered", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // Create account
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }
}
