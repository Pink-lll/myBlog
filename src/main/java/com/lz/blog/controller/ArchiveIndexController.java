package com.lz.blog.controller;

import com.lz.blog.po.Blog;
import com.lz.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * 时间线
 * @author lz
 */
@Controller
public class ArchiveIndexController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archive(Model model){
        Map<String, List<Blog>> archiveMap = blogService.getArchiveMap();
        int count = blogService.countBlog();
        model.addAttribute("archiveMap",archiveMap);
        model.addAttribute("count",count);
        return "archives";
    }
}
