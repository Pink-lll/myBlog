package com.lz.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.blog.po.Blog;
import com.lz.blog.po.Tag;
import com.lz.blog.po.Type;
import com.lz.blog.service.BlogService;
import com.lz.blog.service.TagService;
import com.lz.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author lz
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;

    @GetMapping("/")
    public String index(@RequestParam(required = false,defaultValue = "1",value = "pageNow")int pageNow, Model model) {
        PageHelper.startPage(pageNow, 8);
        List<Blog> blogs = blogService.getIndexBlogs();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        List<Type> types = typeService.getIndexTypeList(6);
        List<Tag> tags = tagService.getIndexTypeList(6);
        List<Blog> recommendBlogs = blogService.getRecommendBlogs(6);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        model.addAttribute("recommendBlogs",recommendBlogs);
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        Blog blog = blogService.getDetailedBlog(id);
        System.out.println(blog.getUser().getAvatar());
        model.addAttribute("blog",blog);
        return "blog";
    }

    @PostMapping("/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pageNow")int pageNow,
                         @RequestParam String query, Model model){

        PageHelper.startPage(pageNow, 5);
        List<Blog> searchBlog = blogService.getSearchBlog(query);
        PageInfo pageInfo = new PageInfo(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }
}
