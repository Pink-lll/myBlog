package com.lz.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.blog.po.Blog;
import com.lz.blog.po.User;
import com.lz.blog.service.BlogService;
import com.lz.blog.service.TagService;
import com.lz.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author lz
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    public void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
    }

    @GetMapping("/blogs")
    public String toBlogs(@RequestParam(required = false,defaultValue = "1",value = "pageNow")int pageNow, Model model){
        PageHelper.startPage(pageNow, 5);
        List<Blog> list = blogService.getBlogList();
        PageInfo<Blog> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        //查询类型和标签
        setTypeAndTag(model);
        return "admin/blogs";
    }


    @GetMapping("/blogs/toAdd")
    public String toAdd(Model model){
        model.addAttribute("blog", new Blog());
        //查询类型和标签
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String addBlog(Blog blog,HttpSession session, RedirectAttributes attributes){
        //设置user属性
        blog.setUser((User)session.getAttribute("user")) ;
        //设置用户id
        blog.setUserId(blog.getUser().getId());
        //设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //给blog中的List<Tag>赋值
        blog.setTags(tagService.getTagByString(blog.getTagIds()));

        //id为空，则为新增
        if (blog.getId() == null) {
            blogService.addBlog(blog);
        } else {
            blogService.updateBlog(blog);
        }

        attributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/admin/blogs";
    }

    /**
     * 跳转到编辑博客
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blogs/{id}/toUpdate")
    public String toUpdate(@PathVariable Long id, Model model){
        Blog blog = blogService.getBlogById(id);
        //将tags列表转换为字符串tagIds
        blog.init();
        //查询类型和标签
        setTypeAndTag(model);
        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin/blogs";
    }

    /**
     * 复合查询
     * @param blog
     * @param pageNow
     * @param model
     * @return
     */
    @PostMapping("/blogs/search")
    public String searchBlogs(@RequestParam(required = false,defaultValue = "1",value = "pageNow")int pageNow, Blog blog, Model model){
        System.out.println("pageNow"+pageNow);
        PageHelper.startPage(pageNow, 5);
        List<Blog> allBlog = blogService.searchAllBlog(blog);
        //得到分页结果对象
        PageInfo pageInfo = new PageInfo(allBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("message", "查询成功");
        setTypeAndTag(model);
        return "admin/blogs :: blogList";
    }

}
