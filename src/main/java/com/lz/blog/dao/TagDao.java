package com.lz.blog.dao;

import com.lz.blog.po.Tag;
import com.lz.blog.po.TagExt;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lz
 */
@Mapper
@Repository
public interface TagDao {
    /**
     * 添加标签
     * @param tag
     * @return
     */
    int addTag(Tag tag);

    /**
     * 通过id获取标签
     * @param id
     * @return
     */
    Tag getTag(Long id);

    /**
     * 获取标签列表
     * @param tagExt
     * @return
     */
    List<Tag> getTagList(TagExt tagExt);

    /**
     * blogController获取标签
     * @return
     */
    List<Tag> getAllTag();


    /**
     * 删除标签
     * @param id
     * @return
     */
    int deleteTag(Long id);


    /**
     * 修改标签
     * @param tag
     * @return
     */
    int updateTag(Tag tag);

    /**
     * 通过名称获取标签
     * @param name
     * @return
     */
    Tag getTagByName(String name);


    /**
     * 获取首页tags
     * @param size
     * @return
     */
    List<Tag> getIndexTags(Integer size);

    /**
     * 在删除博客时同时删除blog_tags表中对应行
     * @param id
     * @return
     */
    int deleteBlogAndTag(Long id);

    /**
     * 在删除标签时同时删除blog_tags表中对应行
     * @param id
     * @return
     */
    int deleteTagAndBlog(Long id);
}
