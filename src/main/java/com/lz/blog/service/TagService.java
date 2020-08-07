package com.lz.blog.service;

import com.github.pagehelper.PageInfo;
import com.lz.blog.po.Tag;
import com.lz.blog.po.TagExt;

import java.util.List;

/**
 * @author lz
 */
public interface TagService {
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
     * 修改标签
     * @param tag
     * @return
     */
    int updateTag(Tag tag);

    /**
     * 删除标签
     * @param id
     * @return
     */
    int deleteTag(Long id);

    /**
     * 获取标签分页
     * @return
     */
    PageInfo<Tag> getTagList(TagExt tagExt);

    /**
     * blogController获取标签列表
     * @return
     */
    List<Tag> getAllTag();

    /**
     * 获取tag
     * @param name
     * @return
     */
    Tag getTagByName(String name);

    /**
     * 获取tag集合
     * @param tagIds
     * @return
     */
    List<Tag> getTagByString(String tagIds);

    /**
     * 将tagsId文本转换为集合
     * @param ids
     * @return
     */
    List<Long> convertToList(String ids);


    /**
     * 获取首页tag
     * @param size
     * @return
     */
    List<Tag> getIndexTypeList(Integer size);
}
