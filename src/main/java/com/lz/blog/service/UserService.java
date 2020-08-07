package com.lz.blog.service;

import com.lz.blog.po.User;

/**
 * @author lz
 */
public interface UserService {
    User checkUser(String username, String password);
}
