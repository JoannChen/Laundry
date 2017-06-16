package com.zuoyu.laundry.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zuoyu.laundry.R;
import com.zuoyu.laundry.application.MyApplication;
import com.zuoyu.laundry.base.BaseActivity;
import com.zuoyu.laundry.widget.NavAnimation;

/**
 * <pre>
 * Function：订单选项卡：未取／未送／已交
 *
 * Created by JoannChen on 2017/6/16 11:26
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class OrderNavActivity extends BaseActivity {

    private NavAnimation navAnimation;

    @Override
    public int setLayout() {
        return R.layout.order_nav_main;
    }

    @Override
    public void initTitle() {
        titleManage.hideTitle();
    }

    @Override
    public void initView() {

        // 返回按钮
        ImageView backImg = id(R.id.iv_back);
        backImg.setOnClickListener(this);

        // 导航
        navAnimation = id(R.id.navAnimation);
        navAnimation.setData(new String[]{getString(R.string.no_take), getString(R.string.no_send), getString(R.string.have_give)});
        navAnimation.setCheckedLineColor(R.color.btn_checked);
        navAnimation.setTextColor(R.color.white, R.color.font_blue);
        navAnimation.setTextSize(50);
        navAnimation.setLineHeight(0);
    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
        MyApplication.setStatusBar(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
