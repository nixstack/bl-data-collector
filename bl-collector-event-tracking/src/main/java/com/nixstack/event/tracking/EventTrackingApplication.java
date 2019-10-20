package com.nixstack.event.tracking;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nixstack.event.tracking.bean.PageDetailBean;
import com.nixstack.event.tracking.bean.PageListActionBean;
import com.nixstack.event.tracking.bean.PageListBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class EventTrackingApplication {
    private  final static Logger logger = LoggerFactory.getLogger(EventTrackingApplication.class);
    private static Random random = new Random();

    // 用户id
    private static String uid ;
    // 作品/课程id...
    private static String tid = "0";


    public static void main(String[] args)  {
        // 控制发送每条的延时时间
        Long delay = args.length > 0 ? Long.parseLong(args[0]) : 0L;

        // 循环遍历次数
        int loop_len = args.length > 1 ? Integer.parseInt(args[1]) : 1000;

        // 生成数据
        generateLog(delay, loop_len);
    }

    private static void generateLog(Long delay, int loop_len) {
        for (int i=0; i<loop_len; i++) {
            int flag = random.nextInt(2);

            switch (flag) {
                case (0):
                    break;
                case (1):
                    JSONObject json = new JSONObject();
                    JSONArray eventsArray = new JSONArray();

                    json.put("source", "pc");

                    // 页面列表
                    if (random.nextBoolean()) {
                        eventsArray.add(genPageListLog());
                    }

                    // 页面列表用户操作
                    if (random.nextBoolean()) {
                        eventsArray.add(genActionLog());
                    }

                    // 详情页
                    if (random.nextBoolean()) {
                        eventsArray.add(genPageDetailLog());
                    }

                    json.put("event", eventsArray);

                    //时间
                    long millis = System.currentTimeMillis();

                    //控制台打印
                    logger.info(millis + "|" + json.toJSONString());
                    break;
            }

            // 延迟
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static Object genPageDetailLog() {
        PageDetailBean pageDetail = new PageDetailBean();

        // 页面入口来源
        int flag = random.nextInt(3);
        pageDetail.setReferrer(flag + "");

        // 状态
        pageDetail.setStatus(random.nextInt(4) + 1 + "");

        // 作品/课程id...
        pageDetail.setTid(tid + "");

        // 页面停留时长
        pageDetail.setStay_time(random.nextInt(10) * random.nextInt(8) + "");

        // 加载时长
        pageDetail.setLoading_time(random.nextInt(10) * random.nextInt(8) + "");

        // 错误码
        flag = random.nextInt(10);
        switch (flag) {
            case 1:
                pageDetail.setError_code("10000");
                break;
            case 2:
                pageDetail.setError_code("20000");
                break;
            case 3:
                pageDetail.setError_code("30000");
                break;
            case 4:
                pageDetail.setError_code("40000");
                break;
            case 5:
                pageDetail.setError_code("50000");
                break;
            default:
                pageDetail.setError_code("");
                break;
        }

        // 分类id
        pageDetail.setCid(random.nextInt(100) + 1 + "");

        return packEventToJson("page_detail", pageDetail);
    }

    private static Object genPageListLog() {
        PageListBean pageList = new PageListBean();

        // 页面状态
        int flag = random.nextInt(3) + 1;

        pageList.setStatus(flag + "");

        // 加载时长
        flag = random.nextInt(10) * random.nextInt(8);
        pageList.setLoading_time(flag + "");

        // 错误码
        flag = random.nextInt(10);
        switch (flag) {
            case 1:
                pageList.setError_code("10000");
                break;
            case 2:
                pageList.setError_code("20000");
                break;
            case 3:
                pageList.setError_code("30000");
                break;
            case 4:
                pageList.setError_code("40000");
                break;
            case 5:
                pageList.setError_code("50000");
                break;
            default:
                pageList.setError_code("");
                break;
        }

        // 加载方式
        flag = random.nextInt(2) + 1;
        pageList.setLoading_way(flag + "");

        // 加载类型
        flag = random.nextInt(3) + 1;
        pageList.setLoading_type(flag + "");

        Object json = JSON.toJSON(pageList);
        return packEventToJson("page_list", json);
    }

    private static Object genActionLog() {
        PageListActionBean pageListAction = new PageListActionBean();
        boolean boolFlag = random.nextInt(10) < 7;

        // 设置状态
        if (boolFlag) {
            pageListAction.setStatus("1");
        } else {
            pageListAction.setStatus("2");
        }

        // 作品/课程id...
        String cur_tid = tid;
        tid  = Integer.valueOf(tid) + 1 + "";
        pageListAction.setTid(cur_tid);

        // 位置
        int i = random.nextInt(6);
        pageListAction.setPlace(i+"");

        // 用户操作类型
        int i2 = random.nextInt(6);
        pageListAction.setType(i2 + "");

        int i1 = random.nextInt(100) + 1;
        pageListAction.setCid(i1 + "");

        Object json = JSON.toJSON(pageListAction);
        return packEventToJson("page_list_action", json);
    }

    private static JSONObject packEventToJson(String eventName, Object json) {
        JSONObject eventJson = new JSONObject();
        eventJson.put("event_time", (System.currentTimeMillis() - random.nextInt(99999999)) + "");
        eventJson.put("event_name", eventName);
        eventJson.put("event_map", json);

        return eventJson;
    }
}
