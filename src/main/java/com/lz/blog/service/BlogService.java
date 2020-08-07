package com.lz.blog.service;

import com.lz.blog.po.Blog;

import java.util.List;
import java.util.Map;

/**
 * 博客列表页Service
 * @author lz
 */
public interface BlogService {
    /**
     * 后台获取博客列表
     * @return
     */
    List<Blog> getBlogList();

    /**
     * 后台通过id搜索博客
     * @param id
     * @return
     */
    Blog getBlogById(Long id);

    /**
     * 查询博客条数
     * @return
     */
    int countBlog();

    /**
     * 添加博客
     * @param blog
     * @return
     */
    int addBlog(Blog blog);

    /**
     * @param blog 修改博客
     * @return
     */
    int updateBlog(Blog blog);

    /**
     * 删除博客
     * @param id
     * @return
     */
    int deleteBlog(Long id);

    /**
     * 复合查询
     * @param blog
     * @return
     */
    List<Blog> searchAllBlog(Blog blog);

    /**
     * 获取推荐博客
     * @param size
     * @return
     */
    List<Blog> getRecommendBlogs(Integer size);

    /**
     * 获取主页博客
     * @return
     */
    List<Blog> getIndexBlogs();

    /**
     * 通过id获取博客详情
     * @param id
     * @return
     */
    Blog getDetailedBlog(Long id);


    /**
     * 通过typeId获取博客列表
     * @param id
     * @return
     */
    List<Blog> getByTypeId(Long id);

    /**
     * 通过tagId获取博客列表
     * @param id
     * @return
     */
    List<Blog> getByTagId(Long id);

    /**
     * 获取年份对应blogs的集合
     * @return
     */
    Map<String,List<Blog>> getArchiveMap();

    /**
     * 搜索blog
     * @param query
     * @return
     */
    List<Blog> getSearchBlog(String query);
}
