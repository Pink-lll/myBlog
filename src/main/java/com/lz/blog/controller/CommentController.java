package com.lz.blog.controller;

import com.lz.blog.po.Comment;
import com.lz.blog.po.User;
import com.lz.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lz
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String commentList(@PathVariable Long blogId, Model model){
        List<Comment> comments = commentService.getCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        //获取博客编号
        Long blogId = comment.getBlog().getId();
        comment.setBlogId(blogId);

        //获取被回复的昵称
        String replyName=comment.getReplyName();
        if(replyName.equals("")){
            comment.setReplyName(null);
        }

        User user = (User)session.getAttribute("user");
        //判断是否为管理员评论
        if(user!=null){
            comment.setAvatar(user.getAvatar());
            comment.setNickname(user.getNickname());
            comment.setEmail(user.getEmail());
            comment.setAdminComment(true);
        }else{
            comment.setAvatar(avatar);
        }
        commentService.addComment(comment);
        return "redirect:/comments/"+blogId;
    }
}
