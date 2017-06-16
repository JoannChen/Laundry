package com.zuoyu.laundry.application;

/**
 * <pre>
 * Function：网络请求Url管理类
 *
 * Created by JoannChen on 2017/3/7 16:01
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Version Information：V 1.0
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class UrlManage {

    /**
     * 正式环境
     */
//    private static final String HOST = "http://cloudcenter.51park.cn/CloudCenter/";

    /**
     * 测试环境
     */
    private static final String HOST = "http://wxtest.61weiqi.com/api/";


    /**
     * 1。登录
     */
    public static final String LOGIN_URL = HOST + "deliveryman/login";

    /**
     * 首页
     */
    public static final String HOME_URL = HOST + "deliveryman/index";


    /**
     * 刷新Token
     */
    public static final String REFRESH_URL = HOST + "authorizations/current";

    /**
     * 公告列表和内容
     */
    public static final String NOTICE_URL = HOST + "deliveryman/notice";


}
