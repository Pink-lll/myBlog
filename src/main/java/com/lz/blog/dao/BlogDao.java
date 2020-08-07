package com.lz.blog.dao;

import com.lz.blog.po.Blog;
import com.lz.blog.po.BlogAndTag;
import com.lz.blog.po.Tag;
import com.lz.blog.po.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lz
 */
@Mapper
@Repository
public interface BlogDao {
    /**
     * 通过id获取博客
     * @param id
     * @return
     */
    Blog getBlogById(Long id);

    /**
     * 后台获取博客列表
     * @return
     */
    List<Blog> getBlogList();

    /**
     * 获取总博客数
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
     * 删除博客
     * @param id
     * @return
     */
    int deleteBlog(Long id);

    /**
     * 修改博客
     * @param blog
     * @return
     */
    int updateBlog(Blog blog);

    /**
     * 复合查询
     * @param blog
     * @return
     */
    List<Blog> searchAllBlog(Blog blog);


    /**
     * 将tagId对应blog保存到t_blog_tags表中
     * @param blogAndTag
     * @return
     */
    int addBlogAndTag(BlogAndTag blogAndTag);

    /**
     * 通过type获取blog
     * @param type
     * @return
     */
    List<Blog> getBlogByType(Type type);

    /**
     * 通过tag获取blog
     * @param tag
     * @return
     */
    List<Blog> getBlogByTag(Tag tag);

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
     * 打开博客详情时增加views
     * @param id
     */
    void addViews(Long id);

    /**
     * 获取年份集合
     * @return
     */
    List<String> getYears();

    /**
     * 获取每年的博客
     * @param year
     * @return
     */
    List<Blog> getBlogsByYear(String year);

    /**
     * 搜索blog
     * @param query
     * @return
     */
    List<Blog> getSearchBlog(String query);
}
