package com.lz.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.blog.dao.BlogDao;
import com.lz.blog.dao.TagDao;
import com.lz.blog.dao.TypeDao;
import com.lz.blog.po.Blog;
import com.lz.blog.po.Tag;
import com.lz.blog.po.TagExt;
import com.lz.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lz
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Autowired
    private BlogDao blogDao;

    @Override
    public int addTag(Tag tag) {
        return tagDao.addTag(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagDao.getTag(id);
    }

    @Override
    public int updateTag(Tag tag) {
        tagDao.updateTag(tag);
        return 0;
    }

    @Override
    public int deleteTag(Long id) {
        Tag tag = new Tag();
        tag.setId(id);
        if(blogDao.getBlogByTag(tag)!=null){
            try {
                throw new Exception("该标签存在对应博客，请先删除对应博客标签");
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        if(tagDao.getTag(id)!=null){
            tagDao.deleteTagAndBlog(id);
            return tagDao.deleteTag(id);
        }
        return 0;
    }

    @Override
    public PageInfo<Tag> getTagList(TagExt tagExt) {
        //设置起始页和页面大小
        PageHelper.startPage(tagExt.getPageNow(),10);

        //获取Tag集合
        List<Tag> list = tagDao.getTagList(tagExt);

        PageInfo<Tag> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    /**
     * blogController获取标签
     * @return
     */
    @Override
    public List<Tag> getAllTag() {
        return tagDao.getAllTag();
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    /**
     * 从tagIds字符串中获取id，根据id获取tag集合
     * @param text
     * @return
     */
    @Override
    public List<Tag> getTagByString(String text) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(text);
        for (Long long1 : longs) {
            tags.add(tagDao.getTag(long1));
        }
        return tags;
    }

    /**
     * 把前端的tagIds字符串转换为list集合
     * @param ids
     * @return
     */
    @Override
    public List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    /**
     * 获取首页的tag
     * @return
     */
    @Override
    public List<Tag> getIndexTypeList(Integer size) {
        List<Tag> tags = tagDao.getIndexTags(size);
        for(Tag tag : tags){
            List<Blog> blogs = blogDao.getBlogByTag(tag);
            tag.setBlogs(blogs);
        }
        return tags;
    }
}
