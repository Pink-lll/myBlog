package com.lz.blog.dao;

import com.lz.blog.po.Type;
import com.lz.blog.po.TypeExt;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lz
 */
@Mapper
@Repository
public interface TypeDao {
    /**
     * 添加分类
     * @param type
     * @return
     */
    int addType(Type type);

    /**
     * 通过id获取分类
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     * 获取分类列表
     * @param typeExt
     * @return
     */
    List<Type> getTypeList(TypeExt typeExt);

    /**
     * blog获取分类
     * @return
     */
    List<Type> getAllType();


    /**
     * 删除分类
     * @param id
     * @return
     */
    int deleteType(Long id);


    /**
     * 修改分类
     * @param type
     * @return
     */
    int updateType(Type type);

    /**
     * 通过名称获取分类
     * @param name
     * @return
     */
    Type getTypeByName(String name);

    /**
     * 首页获取type
     * @param size
     * @return
     */
    List<Type> getIndexTopType(Integer size);
}
