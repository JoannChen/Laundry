package com.zuoyu.laundry.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuoyu.laundry.R;
import com.zuoyu.laundry.application.MyApplication;
import com.zuoyu.laundry.application.UrlManage;
import com.zuoyu.laundry.base.BaseActivity;
import com.zuoyu.laundry.entity.HomeEntity;
import com.zuoyu.laundry.model.TokenAuthModel;
import com.zuoyu.laundry.utils.IntentUtil;
import com.zuoyu.laundry.utils.ToolUtil;
import com.zuoyu.laundry.utils.http.HttpResult;
import com.zuoyu.laundry.widget.CircleImageView;
import com.zuoyu.laundry.widget.NavAnimation;
import com.zuoyu.laundry.widget.RatingBar;


/**
 * <pre>
 * Function：首页
 *
 * Created by JoannChen on 2017/5/22 16:43
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class HomeActivity extends BaseActivity {

    private CircleImageView headImg;

    private RatingBar ratingBar;

    private TextView dayText, monthText;
    private LinearLayout dayLLayout, monthLLayout;

    private TextView takeText, sendText;
    private LinearLayout takeLLayout, sendLLayout;

    private NavAnimation navAnimation;

    @Override
    public int setLayout() {
        return R.layout.home_main;
    }

    @Override
    public void initTitle() {
        titleManage.setLeftBtn(0);
        titleManage.setRightBtn(R.mipmap.icon_more);
        titleManage.setRightBtnSize(63, 52);
        titleManage.setTitleBackground(R.mipmap.bg_home_title);
    }

    @Override
    public void initView() {

        headImg = id(R.id.iv_head);
        ratingBar = id(R.id.ratingBar);

        // 今日提成
        dayText = id(R.id.tv_day);
        dayLLayout = id(R.id.ll_day);
        dayLLayout.setOnClickListener(this);

        // 本月提成
        monthText = id(R.id.tv_month);
        monthLLayout = id(R.id.ll_month);
        monthLLayout.setOnClickListener(this);

        // 未取
        takeText = id(R.id.tv_take);
        takeLLayout= id(R.id.ll_take);
        takeLLayout.setOnClickListener(this);

        // 未送
        sendText = id(R.id.tv_send);
        sendLLayout = id(R.id.ll_send);
        sendLLayout.setOnClickListener(this);

        /*导航*/
        navAnimation = id(R.id.navAnimation);
        navAnimation.setData(new String[]{"公告板", "系统消息"});
        navAnimation.setCheckedLineColor(R.color.btn_checked);
        navAnimation.setTextColor(R.color.btn_checked, R.color.font_gray);
        navAnimation.setTextSize(44);
        navAnimation.setLineHeight(5);


    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
        MyApplication.setStatusBar(this);
        parseHomeInfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_day:
                IntentUtil.start(activity, DayDetailsActivity.class, false);
                break;
            case R.id.ll_month:
                IntentUtil.start(activity, MonthDetailsActivity.class, false);
                break;
            case R.id.ll_take:
                IntentUtil.start(activity, OrderNavActivity.class, IntentUtil.ORDER_NAV, 1, false);
                break;
            case R.id.ll_send:
                IntentUtil.start(activity, OrderNavActivity.class, IntentUtil.ORDER_NAV, 2, false);
                break;
        }
    }

    /**
     * 加载首页数据
     */
    private void parseHomeInfo() {

        httpUtil.get(null, UrlManage.HOME_URL, new HttpResult<HomeEntity>() {
            @Override
            public void onSuccess(HomeEntity result) {

                titleManage.setTitleText(ToolUtil.isEmpty(result.getData().getUsername()) ? getString(R.string.app_name) : (result.getData().getUsername() + "的小店"));
                ratingBar.setRating(result.getData().getStar());

                dayText.setText(String.valueOf(result.getData().getDayAmount()));
                monthText.setText(String.valueOf(result.getData().getMonthAmount()));

                takeText.setText(result.getData().getOrderTakeCount());
                sendText.setText(result.getData().getOrderDeliveryCount());


            }

            @Override
            public void onFailed(int errCord, String errMsg) {

            }
        });


        TokenAuthModel.getInstance().autoRegresh(new TokenAuthModel.OnRefreshTokenListener() {

            @Override
            public void onRefresh() {

            }

            @Override
            public void onRefreshFinish() {

            }
        });
    }
}
