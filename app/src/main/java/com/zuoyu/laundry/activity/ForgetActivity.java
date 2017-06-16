package com.zuoyu.laundry.activity;

import android.os.Bundle;

import com.zuoyu.laundry.application.MyApplication;
import com.zuoyu.laundry.base.BaseActivity;

/**
 * <pre>
 * Function：忘记密码
 *
 * Created by JoannChen on 2017/5/17 17:01
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class ForgetActivity extends BaseActivity {

    @Override
    public int setLayout() {
        return 0;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
        MyApplication.setStatusBar(this);
    }
}
