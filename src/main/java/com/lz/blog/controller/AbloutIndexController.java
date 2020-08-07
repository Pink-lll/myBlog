package com.lz.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 关于我
 * @author lz
 */
@Controller
public class AbloutIndexController {
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
