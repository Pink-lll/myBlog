package com.lz.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.lz.blog.po.Type;
import com.lz.blog.po.TypeExt;
import com.lz.blog.service.TypeService;
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
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String toTypes(@RequestParam(required = false,defaultValue = "1",value = "pageNow")int pageNow, Model model){
        TypeExt typeExt = new TypeExt(pageNow,10);
        PageInfo<Type> pageInfo = typeService.getTypeList(typeExt);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    @GetMapping("/types/toAdd")
    public String toAdd(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String addType(Type type, RedirectAttributes attributes){   //新增
        Type t = typeService.getTypeByName(type.getName());
        if(t != null){
            attributes.addFlashAttribute("msg", "不能添加重复的分类");
            return "redirect:/admin/types/toAdd";
        }else {
            attributes.addFlashAttribute("msg", "添加成功");
        }
        typeService.addType(type);
        return "redirect:/admin/types";   //不能直接跳转到types页面，否则不会显示type数据(没经过types方法)
    }

    @GetMapping("/types/{id}/toUpdate")
    public String toUpdate(@PathVariable Long id,Model model){
        Type type = typeService.getType(id);
        model.addAttribute("type",type);
        return "admin/types-input";
    }

    @PostMapping("/types/{id}")
    public String updateType(@PathVariable Long id, Type type, RedirectAttributes attributes){
        System.out.println("id："+id);
        Type t = typeService.getTypeByName(type.getName());
        if(t != null){
            attributes.addFlashAttribute("msg", "不能添加重复的分类");
            return "redirect:/admin/types/"+id+"/toUpdate";
        }else {
            attributes.addFlashAttribute("msg", "修改成功");
        }
        typeService.updateType(type);
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        System.out.println(id);
        typeService.deleteType(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin/types";
    }
}
