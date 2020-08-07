package com.lz.blog.service;

import com.lz.blog.po.Comment;

import java.util.List;

/**
 * @author lz
 */
public interface CommentService {
    /**
     * 通过blogId获取评论列表
     * @param blogId
     * @return
     */
    List<Comment> getCommentByBlogId(Long blogId);

    /**
     * 保存评论
     * @param comment
     */
    void addComment(Comment comment);
}
