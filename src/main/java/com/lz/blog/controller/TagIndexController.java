package com.lz.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.blog.po.Blog;
import com.lz.blog.po.Tag;
import com.lz.blog.po.Type;
import com.lz.blog.service.BlogService;
import com.lz.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author lz
 */
@Controller
public class TagIndexController {
    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PathVariable Long id, @RequestParam(required = false,defaultValue = "1",value = "pageNow")int pageNow,
                        Model model){
        List<Tag> tags = tagService.getIndexTypeList(10000);
        if(id==-1){
            id = tags.get(0).getId();
        }
        PageHelper.startPage(pageNow, 10000);
        List<Blog> blogs = blogService.getByTagId(id);
        for(Blog blog1 : blogs){
            for(Tag tag : blog1.getTags()){
                System.out.print("41 "+tag+" ");
            }
        }
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("tags",tags);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
