package com.lz.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.blog.po.Blog;
import com.lz.blog.po.Type;
import com.lz.blog.service.BlogService;
import com.lz.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lz
 */
@Controller
public class TypeIndexController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id,@RequestParam(required = false,defaultValue = "1",value = "pageNow")int pageNow,
                        Model model){
        List<Type> types = typeService.getIndexTypeList(10000);
        if(id==-1){
            id = types.get(0).getId();
        }
        PageHelper.startPage(pageNow, 10000);
        List<Blog> blogs = blogService.getByTypeId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
