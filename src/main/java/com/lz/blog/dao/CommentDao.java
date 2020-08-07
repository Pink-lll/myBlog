package com.lz.blog.dao;

import com.lz.blog.po.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lz
 */
@Mapper
@Repository
public interface CommentDao {


    /**
     * 添加评论
     * @param comment
     * @return
     */
    int addComment(Comment comment);

    /**
     * 通过id和blogId获取评论
     * @param id
     * @param blogId
     * @return
     */
    Comment getCommentByIdAndBlogId(@Param("id")Long id,@Param("blogId") Long blogId);

    /**
     * 通过parentCommentId和blogId获取评论集合
     * @param parentCommentId
     * @param blogId
     * @return
     */
    List<Comment> getCommentByParentCommentIdAndBlogId(@Param("parentCommentId")Long parentCommentId, @Param("blogId")Long blogId);

    /**
     * 通过topCommentId和blogId获取comment集合
     * @param topCommentId  顶级评论id
     * @param blogId
     * @return
     */
    List<Comment> getCommentByTopCommentAndBlogId(@Param("topCommentId")Long topCommentId,@Param("blogId")Long blogId);

}
