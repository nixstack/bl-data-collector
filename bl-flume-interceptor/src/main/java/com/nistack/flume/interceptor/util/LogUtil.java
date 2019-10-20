package com.nistack.flume.interceptor.util;

import org.apache.commons.lang.math.NumberUtils;

public class LogUtil {
    public static boolean validateEvent(String log) {
        if (log == null) {
            return false;
        }

        // timestamp|json
        String[] split = log.split("\\|");
        if (split.length != 2) {
            return false;
        }
        if (split[0].length() != 13 || !NumberUtils.isDigits(split[0])) {
            return false;
        }
        if (!split[1].trim().startsWith("{") || !split[1].trim().endsWith("}")) {
            return false;
        }

        // 只有为true才会拦截到数据
        return true;
    }
}
