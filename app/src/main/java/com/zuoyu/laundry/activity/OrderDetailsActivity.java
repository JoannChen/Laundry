package com.zuoyu.laundry.activity;

import android.os.Bundle;

import com.zuoyu.laundry.R;
import com.zuoyu.laundry.application.MyApplication;
import com.zuoyu.laundry.base.BaseActivity;
import com.zuoyu.laundry.widget.OrderItemView;


/**
 * <pre>
 * Function：订单详情
 *
 * Created by JoannChen on 2017/6/14 11:15
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class OrderDetailsActivity extends BaseActivity {

    private OrderItemView orderItemView;

    @Override
    public int setLayout() {
        return R.layout.order_details_main;
    }

    @Override
    public void initTitle() {
        titleManage.setTitleText("订单详情");
    }

    @Override
    public void initView() {
        orderItemView = (OrderItemView) findViewById(R.id.orderItemView);
        orderItemView.setName("黑色风衣");
        orderItemView.setMoney("100");

    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
        MyApplication.setStatusBar(this);
    }
}
