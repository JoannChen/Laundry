package com.zuoyu.laundry.activity;

import android.os.Bundle;

import com.zuoyu.laundry.R;
import com.zuoyu.laundry.application.MyApplication;
import com.zuoyu.laundry.base.BaseActivity;


/**
 * <pre>
 * Function：提现记录界面
 *
 * Created by JoannChen on 2017/6/14 11:15
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * </pre>
 */
public class WithdrawRecordActivity extends BaseActivity {

    @Override
    public int setLayout() {
        return R.layout.withdraw_record_main;
    }

    @Override
    public void initTitle() {
        titleManage.setTitleText(getString(R.string.cash));

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
