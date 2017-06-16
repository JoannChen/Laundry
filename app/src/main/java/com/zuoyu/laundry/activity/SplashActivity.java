package com.zuoyu.laundry.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.jaeger.library.StatusBarUtil;
import com.zuoyu.laundry.R;
import com.zuoyu.laundry.application.MyApplication;
import com.zuoyu.laundry.base.BaseActivity;

/**
 * <pre>
 * Function：启动页
 *
 * Created by JoannChen on 2017/4/28 13:58
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class SplashActivity extends BaseActivity {

    @Override
    public int setLayout() {
        return 0;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
        StatusBarUtil.setColor(this, ContextCompat.getColor(context, R.color.bg_theme));
    }

    @Override
    public void initView() {

    }
}
