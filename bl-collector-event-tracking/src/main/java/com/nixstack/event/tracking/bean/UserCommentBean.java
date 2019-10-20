package com.nixstack.event.tracking.bean;

import com.nixstack.event.tracking.base.UserBaseBean;
import lombok.Data;

/**
 * 用户评论
 */
@Data
public class UserCommentBean extends UserBaseBean {
    private String comment_id; // 评论id
    private String tid; // 点赞的对象id, 作品/课程id...
    private  String comment_parent_id; // 父级评论id(为0则是一级评论,不为0则是回复)
    private String comment_content; //评论内容
    private String create_time; // 创建时间
    private int comment_praise_count; // 点赞数量
    private int comment_reply_count; // 回复数量

}
