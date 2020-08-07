package com.lz.blog.service;

import com.github.pagehelper.PageInfo;
import com.lz.blog.po.Tag;
import com.lz.blog.po.Type;
import com.lz.blog.po.TypeExt;

import java.util.List;

/**
 * @author lz
 */
public interface TypeService {

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
     * 修改分类
     * @param type
     * @return
     */
    int updateType(Type type);

    /**
     * 删除分类
     * @param id
     * @return
     */
    int deleteType(Long id);

    /**
     * 获取分类分页
     * @return
     */
    PageInfo<Type> getTypeList(TypeExt typeExt);

    /**
     * blogController获取类型
     * @return
     */
    List<Type> getAllType();

    Type getTypeByName(String name);

    /**
     * 获取首页前多少的type
     * @param size
     * @return
     */
    List<Type> getIndexTypeList(Integer size);


}
