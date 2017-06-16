package com.zuoyu.laundry.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.zuoyu.laundry.R;
import com.zuoyu.laundry.application.MyApplication;
import com.zuoyu.laundry.base.BaseActivity;
import com.zuoyu.laundry.widget.HorizontalListView;

/**
 * <pre>
 * Function：日收成界面
 *
 * Created by JoannChen on 2017/6/15 18:27
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class DayDetailsActivity extends BaseActivity {

    HorizontalListView horizontalListView;

    // 月总收成// 成单数 // 成单提成高
    private TextView incomeText, countText, commissionText;


    @Override
    public int setLayout() {
        return R.layout.month_main;
    }

    @Override
    public void initTitle() {
        titleManage.setTitleText(getString(R.string.day_income_details));
    }

    @Override
    public void initView() {

        TextView descText = id(R.id.text_1);
        descText.setText(getString(R.string.day_total_income));

        horizontalListView = (HorizontalListView) findViewById(R.id.listView);

        incomeText = (TextView) findViewById(R.id.tv_income);
        countText = (TextView) findViewById(R.id.tv_count);
        commissionText = (TextView) findViewById(R.id.tv_commission);

        incomeText.setText("371.00");
        countText.setText("28");
        commissionText.setText("124.00");

    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
        MyApplication.setStatusBar(this);
    }
}
