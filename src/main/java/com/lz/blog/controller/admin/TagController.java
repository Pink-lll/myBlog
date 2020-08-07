package com.lz.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.lz.blog.po.Tag;
import com.lz.blog.po.TagExt;
import com.lz.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author lz
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String toTags(@RequestParam(required = false,defaultValue = "1",value = "pageNow")int pageNow, Model model){
        TagExt tagExt = new TagExt(pageNow,10);
        PageInfo<Tag> pageInfo = tagService.getTagList(tagExt);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }

    @GetMapping("/tags/toAdd")
    public String toAdd(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String addTag(Tag tag, RedirectAttributes attributes){   //新增
        Tag t = tagService.getTagByName(tag.getName());
        if(t != null){
            attributes.addFlashAttribute("msg", "不能添加重复的分类");
            return "redirect:/admin/tags/toAdd";
        }else {
            attributes.addFlashAttribute("msg", "添加成功");
        }
        tagService.addTag(tag);
        return "redirect:/admin/tags";   //不能直接跳转到tags页面，否则不会显示tag数据(没经过tags方法)
    }

    @GetMapping("/tags/{id}/toUpdate")
    public String toUpdate(@PathVariable Long id,Model model){
        Tag tag = tagService.getTag(id);
        model.addAttribute("tag",tag);
        return "admin/tags-input";
    }

    @PostMapping("/tags/{id}")
    public String updateTag(@PathVariable Long id, Tag tag, RedirectAttributes attributes){
        Tag t = tagService.getTagByName(tag.getName());
        if(t != null){
            attributes.addFlashAttribute("msg", "不能添加重复的分类");
            return "redirect:/admin/tags/"+id+"/toUpdate";
        }else {
            attributes.addFlashAttribute("msg", "修改成功");
        }
        tagService.updateTag(tag);
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin/tags";
    }
}
