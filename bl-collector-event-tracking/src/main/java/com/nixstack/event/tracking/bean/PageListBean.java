package com.nixstack.event.tracking.bean;

import com.nixstack.event.tracking.base.PageBaseBean;
import lombok.Data;

/**
 * 列表页
 */
@Data
public class PageListBean extends PageBaseBean {
    private String loading_time; // 加载时长：计算下拉开始到接口返回数据的时间
    private String loading_way; // 加载方式：1-读取缓存，2-从接口拉新数据
    private String loading_type;// 加载类型：自动加载=1，用户下拽加载=2，底部加载=3（底部条触发点击底部提示条/点击返回顶部加载）
}
