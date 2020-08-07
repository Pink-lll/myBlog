package com.lz.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.blog.dao.BlogDao;
import com.lz.blog.dao.TypeDao;
import com.lz.blog.po.Type;
import com.lz.blog.po.TypeExt;
import com.lz.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lz
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;
    
    @Autowired
    private BlogDao blogDao;

    @Override
    public int addType(Type type) {
        return typeDao.addType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    @Override
    public int updateType(Type type) {
        typeDao.updateType(type);
        return 0;
    }

    @Override
    public int deleteType(Long id) {
        Type type = new Type();
        type.setId(id);
        if(blogDao.getBlogByType(type)!=null){
            try {
                throw new Exception("该类型存在对应博客，请先删除博客类型");
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        if(typeDao.getType(id)!=null){
            return typeDao.deleteType(id);
        }
        return 0;
    }

    @Override
    public PageInfo<Type> getTypeList(TypeExt typeExt) {
        //设置起始页和页面大小
        PageHelper.startPage(typeExt.getPageNow(),10);

        //获取type集合
        List<Type> list = typeDao.getTypeList(typeExt);

        PageInfo<Type> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    /**
     * blogController获取type
     * @return
     */
    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    public List<Type> getIndexTypeList(Integer size) {
        System.out.println("执行前"+size);
        List<Type> types = typeDao.getIndexTopType(size);
        System.out.println("执行到了");
        for(Type type : types){
            type.setBlogs(blogDao.getBlogByType(type));
        }
        return types;
    }
}
