package com.zuoyu.laundry.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.zuoyu.laundry.R;
import com.zuoyu.laundry.base.BaseAdapter;
import com.zuoyu.laundry.base.ViewHolder;

import java.util.List;

/**
 * <pre>
 * Function：月收成明细适配器（横向ListView）
 *
 * Created by JoannChen on 2017/5/22 14:38
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class MonthAdapter extends BaseAdapter {

   private int mSelect = 0;   //选中项

    public MonthAdapter(Context context, List<?> list) {
        super(context, list);
    }

    public void changeSelected(int position) { //刷新方法
        if (position != mSelect) {
            mSelect = position;
            notifyDataSetChanged();
        }
    }

    @Override
    public int setLayout() {
        return R.layout.month_item;
    }

    @Override
    public void getView(ViewHolder holder) {

        String str = (String) list.get(holder.getPosition());

        TextView monthText = holder.getView(R.id.tv_month);
        monthText.setText(str);

        if (mSelect == holder.getPosition()) {
            monthText.setBackgroundResource(R.drawable.shape_bg_month);  //选中项背景
            monthText.setTextColor(ContextCompat.getColor(context,R.color.white));
        } else {
            monthText.setBackgroundResource(R.mipmap.bg_transparent);  //其他项背景
            monthText.setTextColor(ContextCompat.getColor(context,R.color.font_black));
        }


    }
}
