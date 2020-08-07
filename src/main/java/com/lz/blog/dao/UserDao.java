package com.lz.blog.dao;

import com.lz.blog.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * @author lz
 */
@Mapper
@Repository
public interface UserDao {
    User checkUser(@Param("username") String username, @Param("password") String password);
}
