package com.zuoyu.laundry.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zuoyu.laundry.R;

/**
 * 功能:
 * 创建人: 张野
 * 创建时间: 2017/6/14.
 * QQ: 154151222
 * EMAIL: zhangye98@foxmail.com
 */
public class OrderItemView extends RelativeLayout implements View.OnClickListener {
    private TextView text_name, text_money, text_count;
    private ImageView view_add, view_cut;


    public OrderItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_order_item, this);
        text_name = (TextView) findViewById(R.id.text_name);
        text_money = (TextView) findViewById(R.id.text_money);
        text_count = (TextView) findViewById(R.id.text_count);
        view_add = (ImageView) findViewById(R.id.view_add);
        view_add.setOnClickListener(this);
        view_cut = (ImageView) findViewById(R.id.view_cut);
        view_cut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String count = text_count.getText().toString();

        switch (v.getId()) {
            case R.id.view_cut:
                if (count.equals("")) {
                    return;
                }

                if(count.equals("1")){
                    view_cut.setVisibility(View.GONE);
                    text_count.setText("");
                }else{
                    int mCount = Integer.parseInt(count);
                    text_count.setText(String.valueOf((mCount - 1)));
                }

                break;

            case R.id.view_add:

                if (count.equals("99")) {
                    return;
                }

                if(count.equals("")){
                    count = "0";
                    view_cut.setVisibility(View.VISIBLE);
                }

                int mCount = Integer.parseInt(count);
                text_count.setText(String.valueOf((mCount + 1)));
                break;
        }
    }

    public void setName(String name) {
        text_name.setText(name);
    }

    public void setMoney(String money) {
        text_money.setText("￥ " + money);
    }
}
