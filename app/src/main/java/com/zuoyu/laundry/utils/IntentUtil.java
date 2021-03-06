package com.zuoyu.laundry.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

/**
 * <pre>
 * Function：Intent 跳转工具类
 *
 * Created by JoannChen on 2017/3/29 09:47
 * QQ:411083907
 * E-mail:q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class IntentUtil {

    /**
     * 订单选项卡标识
     * 1。未取
     * 2。未送
     */
    public static String  ORDER_NAV = "orderNav";


    /**
     * 传递对象
     *
     * @param activity Activity
     * @param clazz    Activity
     * @param key      键名
     * @param obj      键值（对象，实现Serializable接口）
     * @param isFinish 是否关闭
     */
    public static void start(Activity activity, Class<?> clazz, String key, Object obj, boolean isFinish) {
        Intent intent = new Intent(activity, clazz);
        Bundle bundle = new Bundle();
        bundle.putSerializable(key, (Serializable) obj);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        if (isFinish) {
            activity.finish();
        }
    }


    /**
     * Intent跳转
     *
     * @param activity Activity
     * @param clazz    Activity
     * @param isFinish 是否关闭
     */
    public static void start(Activity activity, Class<?> clazz,
                             boolean isFinish) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
        if (isFinish) {
            activity.finish();
        }
    }

    public static void start(Activity activity, Class<?> clazz, String key, String value,
                             boolean isFinish) {
        Intent intent = new Intent(activity, clazz);
        intent.putExtra(key, value);
        activity.startActivity(intent);
        if (isFinish) {
            activity.finish();
        }
    }

    public static void start(Activity activity, Class<?> clazz, String key, int value,
                             boolean isFinish) {
        Intent intent = new Intent(activity, clazz);
        intent.putExtra(key, value);
        activity.startActivity(intent);
        if (isFinish) {
            activity.finish();
        }
    }

    public static void start(Activity activity, Class<?> clazz, String key, boolean value,
                             boolean isFinish) {
        Intent intent = new Intent(activity, clazz);
        intent.putExtra(key, value);
        activity.startActivity(intent);
        if (isFinish) {
            activity.finish();
        }
    }

}
