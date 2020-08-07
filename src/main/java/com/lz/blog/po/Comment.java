package com.lz.blog.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lz
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private boolean adminComment;  //是否为管理员评论

    //头像
    private String avatar;
    private Date createTime;

    private Long blogId;
    //父评论id
    private Long parentCommentId;
    //被回复人昵称
    private String replyName;
    //记录回复的编号
    private Long topCommentId;

    //子评论
    private List<Comment> childComments = new ArrayList<>();

    private Blog blog;

}
