package com.icore.winvaz.winvazcommon.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author wdq
 * @Create 2021/5/26 16:11
 * @Version 1.0.0
 */
public class Helper {

    private static final String CHINESE_PATTERN = "[\u4e00-\u9fa5]";

    /**
     * 判断字符串是否包含中文
     * @author wdq
     * @create 2021/5/26 16:12
     * @param str
     * @Return boolean
     * @exception
     */
    public static boolean isContainChinese(String str) {
        Pattern pattern = Pattern.compile(CHINESE_PATTERN);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return true;
        }
        return false;
    }
}
