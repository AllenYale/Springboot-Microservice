package com.travischenn.commonweb.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class RegexpUtil {

    /**
     * 判断是否是合法的手机号码
     *
     * @param mobile 手机号码
     * @return true 合法 | false 非法
     */
    public static boolean isMobile(String mobile) {
        boolean flag = false;
        if (mobile.length() == 0) {
            return false;
        }
        String[] mobiles = mobile.split(",");
        int len = mobiles.length;
        if (len == 1) {
            return Pattern.matches("^((13[0-9])|(14[5,7,9])|(15[^4,\\D])|(17[0,1,3,5-8])|(18[0-9]))\\d{8}$", mobile);
        } else {
            for (String mobile_item : mobiles) {
                if (isMobile(mobile_item)) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * 判断是否是合法的手机号码
     *
     * @param url 手机号码
     * @return true 合法 | false 非法
     */
    public static boolean isUrl(String url) {
        return url.length() != 0 && Pattern.matches("(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*)*(\\/?)([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\\\+&amp;%\\$#_]*)?", url);
    }

    /**
     * 判断 URL 是否符合OSS路径标准
     *
     * @param url         手机号码
     * @param oss_prefix  OSS路径前缀
     * @return true 合法 | false 非法
     */
    public static boolean isValidOSSUrl(String url , String oss_prefix) {

        boolean flag = true;

        if(!isUrl(url)){
            flag = false;
        }

        if(!StringUtils.startsWith(url , oss_prefix)){
            flag = false;
        }

        return flag;
    }



}
