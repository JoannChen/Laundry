package com.zuoyu.laundry.utils;

import android.text.TextUtils;
import android.util.Log;

import java.util.Locale;


/**
 * <pre>
 * Function：Log打印工具类
 *
 * Created by JoannChen on 2017/2/6 11:46
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class LogUtil {

    // 自定义Tag的前缀，可以是作者名
    private static String customTagPrefix = "Joann ==========";
    private static CustomLogger customLogger = null;

    private static boolean allowD = true;//debug
    private static boolean allowE = true;//error
    private static boolean allowI = true;//info
    private static boolean allowV = true;//verbose
    private static boolean allowW = true;//warn
/*    private static boolean allowWtf = true;// wtf的意思是what a terrible failure可怕的失败：报告一个永远不可能发生的情况。*/


    public static void d(String content) {
        if (allowD) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.d(tag, content);
            } else {
                Log.d(tag, content);
            }

        }
    }

    public static void e(String content) {
        if (allowE) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.e(tag, content);
            } else {
                Log.e(tag, content);
            }

        }
    }

    public static void i(String content) {
        if (allowI) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.i(tag, content);
            } else {
                Log.i(tag, content);
            }

        }
    }

    public static void v(String content) {
        if (allowV) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.v(tag, content);
            } else {
                Log.v(tag, content);
            }

        }
    }

    public static void w(String content) {
        if (allowW) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.w(tag, content);
            } else {
                Log.w(tag, content);
            }

        }
    }

    /**
     * 关闭所有Log日志
     */
    public static void closeAllLog() {
        allowD = false;
        allowE = false;
        allowI = false;
        allowV = false;
        allowW = false;
        customTagPrefix = "";
    }


    /**
     * 生成Tag
     *
     * @param caller StackTraceElement
     * @return string
     */
    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(L:%d)"; // 占位符
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(Locale.getDefault(), tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }


    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

    /**
     * 接口
     */
    private interface CustomLogger {

        void d(String var1, String var2);

        void e(String var1, String var2);

        void i(String var1, String var2);

        void v(String var1, String var2);

        void w(String var1, String var2);

    }
}
