package com.lz.blog.service.impl;

import com.lz.blog.dao.CommentDao;
import com.lz.blog.po.Comment;
import com.lz.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author lz
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> getCommentByBlogId(Long blogId) {
        //获取该博客下的所有的最高级评论
        List<Comment> topComments=commentDao.getCommentByParentCommentIdAndBlogId((long)-1,blogId);

        for(Comment topComment:topComments){
            Long topCommentId=topComment.getId();
            topComment.setChildComments(commentDao.getCommentByTopCommentAndBlogId(topCommentId,blogId));
        }
        return topComments;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addComment(Comment comment) {
        //获取父亲评论
        Long parentCommentId = comment.getParentCommentId();
        if(parentCommentId==-1){
            comment.setParentCommentId(parentCommentId);
            comment.setTopCommentId((long) -1);
        }else{
            comment.setParentCommentId(parentCommentId);
            //查询上一级评论或回复的情况
            Comment comment1 = commentDao.getCommentByIdAndBlogId(parentCommentId,comment.getBlogId());
            if(comment1.getTopCommentId()==-1){
                //表明它的上一级是最高级评论
                comment.setTopCommentId(comment1.getId());
            }
            else{
                comment.setTopCommentId(comment1.getTopCommentId());
            }
        }

        comment.setCreateTime(new Date());
        commentDao.addComment(comment);
    }
}
