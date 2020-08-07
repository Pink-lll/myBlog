package com.lz.blog.service.impl;

import com.lz.blog.dao.BlogDao;
import com.lz.blog.dao.TagDao;
import com.lz.blog.dao.TypeDao;
import com.lz.blog.exception.NotFoundException;
import com.lz.blog.po.Blog;
import com.lz.blog.po.BlogAndTag;
import com.lz.blog.po.Tag;
import com.lz.blog.po.Type;
import com.lz.blog.service.BlogService;
import com.lz.blog.service.TagService;
import com.lz.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author lz
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private TagDao tagDao;

    @Autowired
    private TypeDao typeDao;

    @Autowired
    private TagService tagService;

    /**
     * 后台复合查询博客列表
     * @return
     */
    @Override
    public List<Blog> getBlogList() {
        return blogDao.getBlogList();
    }

    /**
     * 后台通过id获取博客
     * @param id
     * @return
     */
    @Override
    public Blog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    /**
     * 获取博客数
     * @return
     */
    @Override
    public int countBlog() {
        return blogDao.countBlog();
    }

    /**
     * 添加博客
     * @param blog
     * @return
     */
    @Override
    public int addBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blogDao.addBlog(blog);
        //保存博客后才能获取自增的id
        Long id = blog.getId();
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            //新增时无法获取自增的id,在mybatis里修改
            blogAndTag = new BlogAndTag(tag.getId(), id);
            blogDao.addBlogAndTag(blogAndTag);
        }
        return 1;
    }

    /**
     * 修改博客
     * @param blog 修改博客
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateBlog(Blog blog) {
        Blog b = blogDao.getBlogById(blog.getId());
        if(b==null){
            throw new NotFoundException("该博客不存在");
        }
        blog.setUpdateTime(new Date());
        tagDao.deleteBlogAndTag(blog.getId());
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(), blog.getId());
            blogDao.addBlogAndTag(blogAndTag);
        }
        return blogDao.updateBlog(blog);
    }

    /**
     * 删除博客
     * @param id
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public int deleteBlog(Long id) {
        tagDao.deleteBlogAndTag(id);
        return blogDao.deleteBlog(id);
    }

    @Override
    public List<Blog> searchAllBlog(Blog blog) {
        return blogDao.searchAllBlog(blog);
    }

    @Override
    public List<Blog> getRecommendBlogs(Integer size) {
        return blogDao.getRecommendBlogs(size);
    }

    @Override
    public List<Blog> getIndexBlogs() {
        return blogDao.getIndexBlogs();
    }

    @Override
    public Blog getDetailedBlog(Long id) {
        Blog blog = blogDao.getDetailedBlog(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogDao.addViews(id);
        return blog;
    }

    @Override
    public List<Blog> getByTypeId(Long id) {
        Type type = new Type();
        type.setId(id);
        return blogDao.getBlogByType(type);
    }

    @Override
    public List<Blog> getByTagId(Long id) {
        Tag tag = new Tag();
        tag.setId(id);
        List<Blog> blogs = blogDao.getBlogByTag(tag);
        for(Blog blog : blogs){
            List<Long> tags = tagService.convertToList(blog.getTagIds());
            List<Tag> list = new ArrayList<>();
            for(Long l : tags){
                Tag tag1 = tagDao.getTag(l);
                list.add(tag1);
            }
            blog.setTags(list);
            Type type = typeDao.getType(blog.getTypeId());
            blog.setType(type);
            blog.setTypeId(type.getId());
        }
        return blogs;
    }

    /**
     * 获取年份对应blogs的map
     * @return
     */
    @Override
    public Map<String, List<Blog>> getArchiveMap() {
        List<String> years = blogDao.getYears();
        Set<String> set = new HashSet<>(years);
        Map<String,List<Blog>> archiveMap = new HashMap<>(10);
        for(String year : set){
            List<Blog> blogs = blogDao.getBlogsByYear(year);
            archiveMap.put(year,blogs);
        }
        return archiveMap;
    }

    @Override
    public List<Blog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }
}
