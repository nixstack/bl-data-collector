package com.nixstack.event.tracking.bean;

import com.nixstack.event.tracking.base.PageBaseBean;
import lombok.Data;

/**
 * 详情页
 */
@Data
public class PageDetailBean extends PageBaseBean {
    private String tid; // 标识，作品/课程id...
    private String stay_time; // 页面停留时长：从商品开始加载时开始计算，到用户关闭页面所用的时间。
    private String loading_time; // 加载时长：计算页面开始加载到接口返回数据的时间
    private String cid; // 分类ID
}
