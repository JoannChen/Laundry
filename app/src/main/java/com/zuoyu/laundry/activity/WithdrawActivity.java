package com.zuoyu.laundry.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.zuoyu.laundry.R;
import com.zuoyu.laundry.base.BaseActivity;
import com.zuoyu.laundry.utils.ViewUtil;

/**
 * <pre>
 * Function：
 *
 * Created by JoannChen on 2017/5/22 16:52
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class WithdrawActivity extends BaseActivity {

    @Override
    public int setLayout() {
        return R.layout.withdraw_main;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {

        // 提现规则，动态设置DrawableLeft图标大小
        TextView wornText = (TextView) findViewById(R.id.tv_warn);
        Drawable drawable = ContextCompat.getDrawable(context, R.mipmap.icon_warn);
        drawable.setBounds(0, 0, ViewUtil.getHeight(60), ViewUtil.getHeight(60));
        wornText.setCompoundDrawables(drawable, null, null, null);

    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {

    }
}
