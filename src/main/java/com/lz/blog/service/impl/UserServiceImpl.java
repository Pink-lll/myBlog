package com.lz.blog.service.impl;

import com.lz.blog.dao.UserDao;
import com.lz.blog.po.User;
import com.lz.blog.service.UserService;
import com.lz.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author lz
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        return userDao.checkUser(username, MD5Utils.code(password));
    }
}
