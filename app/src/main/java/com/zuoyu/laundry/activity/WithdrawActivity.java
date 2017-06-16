package com.zuoyu.laundry.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zuoyu.laundry.R;
import com.zuoyu.laundry.application.MyApplication;
import com.zuoyu.laundry.base.BaseActivity;
import com.zuoyu.laundry.utils.IntentUtil;
import com.zuoyu.laundry.utils.ViewUtil;

/**
 * <pre>
 * Function：提现界面
 *
 * Created by JoannChen on 2017/5/22 16:52
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class WithdrawActivity extends BaseActivity {

    private TextView balanceText;
    private TextView ruleText;

    private Button withdrawBtn;

    @Override
    public int setLayout() {
        return R.layout.withdraw_main;
    }

    @Override
    public void initTitle() {
        titleManage.setTitleText(getString(R.string.withdraw));
        titleManage.setRightText(getString(R.string.record));
        titleManage.setRightBtnOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.start(activity, WithdrawRecordActivity.class, false);
            }
        });
    }

    @Override
    public void initView() {

        // 账户余额
        balanceText = id(R.id.tv_balance);


        // 提示按钮，动态设置DrawableLeft图标大小
        TextView wornText = id(R.id.tv_warn);
        Drawable drawable = ContextCompat.getDrawable(context, R.mipmap.icon_warn);
        drawable.setBounds(0, 0, ViewUtil.getHeight(60), ViewUtil.getHeight(60));
        wornText.setCompoundDrawables(drawable, null, null, null);

        // 提现规则
        ruleText = id(R.id.tv_rule);

        // 提现按钮
        withdrawBtn = id(R.id.btn_withdraw);
        withdrawBtn.setOnClickListener(this);

    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {
        MyApplication.addActivity(this);
        MyApplication.setStatusBar(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_withdraw:
                break;
        }
    }
}
