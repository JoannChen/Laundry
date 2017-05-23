package com.zuoyu.laundry.activity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.zuoyu.laundry.R;
import com.zuoyu.laundry.adapter.MonthAdapter;
import com.zuoyu.laundry.base.BaseActivity;
import com.zuoyu.laundry.widget.HorizontalListView;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Function：月收成明细
 *
 * Created by JoannChen on 2017/5/22 11:15
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class MonthDetailsActivity extends BaseActivity implements GestureDetector.OnGestureListener {

    HorizontalListView horizontalListView;
    List<String> list = new ArrayList<>();
    MonthAdapter adapter;

    // 月总收成// 成单数 // 成单提成高
    private TextView incomeText, countText, commissionText;

    @Override
    public int setLayout() {
        return R.layout.month_main;
    }

    @Override
    public void initTitle() {
        titleManage.setTitleText("月收成明细");
        titleManage.setRightText("提现", R.color.white);
        titleManage.setRightBtnOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void initView() {

        initData();

        horizontalListView = (HorizontalListView) findViewById(R.id.listView);

        incomeText = (TextView) findViewById(R.id.tv_income);
        countText = (TextView) findViewById(R.id.tv_count);
        commissionText = (TextView) findViewById(R.id.tv_commission);


        incomeText.setText("371.00");
        countText.setText("28");
        commissionText.setText("124.00");


        adapter = new MonthAdapter(context, list);
        horizontalListView.setAdapter(adapter);


        horizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                adapter.changeSelected(position);//刷新
            }
        });
    }


    private void initData() {
        list.add("一月");
        list.add("二月");
        list.add("三月");
        list.add("四月");
        list.add("五月");
        list.add("六月");
        list.add("七月");
        list.add("八月");
        list.add("九月");
        list.add("十月");
        list.add("十一月");
        list.add("十二月");
    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {

    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}
