package com.nixstack.event.tracking.base;

import lombok.Data;

/**
 * 页面基类
 */
@Data
public class PageBaseBean {
    private String status; // 状态：开始加载=1，加载成功=2，加载失败=3，加载退出=4
    private String referrer; // 进入页面的源头
    private String error_code; // 加载失败码：把加载失败状态码报回来（报空为加载成功，没有失败）
}
