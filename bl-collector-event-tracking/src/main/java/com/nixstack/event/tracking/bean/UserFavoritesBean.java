package com.nixstack.event.tracking.bean;

import com.nixstack.event.tracking.base.UserBaseBean;
import lombok.Data;

/**
 * 用户收藏
 */
@Data
public class UserFavoritesBean extends UserBaseBean {
    private String id; // 主键
    private String tid; // 收藏的作品/课程id...
    private String create_time; // 创建时间

}
